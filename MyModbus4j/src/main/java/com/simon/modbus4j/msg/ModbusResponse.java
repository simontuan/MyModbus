package com.simon.modbus4j.msg;

import com.simon.modbus4j.base.ModbusUtils;
import com.simon.modbus4j.code.ExceptionCode;
import com.simon.modbus4j.code.FunctionCode;
import com.simon.modbus4j.exception.IllegalFunctionException;
import com.simon.modbus4j.exception.ModbusTransportException;
import com.simon.modbus4j.exception.SlaveIdNotEqual;
import com.simon.modbus4j.sero.util.queue.ByteQueue;

abstract public class ModbusResponse extends ModbusMessage {
    protected static final byte MAX_FUNCTION_CODE = (byte) 0x80;

    public static ModbusResponse createModbusResponse(ByteQueue queue) throws ModbusTransportException {
        int slaveId = ModbusUtils.popUnsignedByte(queue);
        byte functionCode = queue.pop();
        boolean isException = false;

        if(greaterThan(functionCode, MAX_FUNCTION_CODE)){
            isException = true;
            functionCode -= MAX_FUNCTION_CODE;
        }

        ModbusResponse response = null;
        if(functionCode == FunctionCode.READ_COILS){
            response = new ReadCoilsResponse(slaveId);
        }else if(functionCode == FunctionCode.READ_DISCRETE_INPUTS){
            response = new ReadDiscreteInputsResponse(slaveId);
        }else if(functionCode == FunctionCode.READ_HOLDING_REGISTERS){
            response = new ReadHoldingRegistersResponse(slaveId);
        }else if(functionCode == FunctionCode.READ_INPUT_REGISTERS){
            response = new ReadInputRegistersResponse(slaveId);
        }else if(functionCode == FunctionCode.WRITE_COIL){
            response = new WriteCoilResponse(slaveId);
        }else if(functionCode == FunctionCode.WRITE_REGISTER){
            response = new WriteRegisterResponse(slaveId);
        }else if(functionCode == FunctionCode.READ_EXCEPTION_STATUS){
            response = new ReadExceptionStatusResponse(slaveId);
        }else if(functionCode == FunctionCode.WRITE_COILS){
            response = new WriteCoilsResponse(slaveId);
        }else if(functionCode == FunctionCode.WRITE_REGISTERS){
            response = new WriteRegistersResponse(slaveId);
        }else if(functionCode == FunctionCode.REPORT_SLAVE_ID){
            response = new ReportSlaveIdResponse(slaveId);
        }else if(functionCode == FunctionCode.WRITE_MASK_REGISTER){
            response = new WriteMaskRegisterResponse(slaveId);
        }else{
            throw new IllegalFunctionException(functionCode, slaveId);
        }

        response.read(queue, isException);

        return response;
    }

    protected byte exceptionCode = -1;

    ModbusResponse(int slaveId) throws ModbusTransportException{
        super(slaveId);
    }

    public boolean isException(){
        return exceptionCode != -1;
    }

    public String getExceptionMessage(){
        return ExceptionCode.getExceptionMessage(exceptionCode);
    }

    void setException(byte exceptionCode){
        this.exceptionCode = exceptionCode;
    }

    public byte getExceptionCode(){
        return exceptionCode;
    }

    @Override
    final protected void writeImpl(ByteQueue queue){
        if(isException()){
            queue.push((byte)(getFunctionCode() + MAX_FUNCTION_CODE));
        }else {
            queue.push(getExceptionCode());
            writeResponse(queue);
        }
    }

    abstract protected void writeResponse(ByteQueue queue);

    void read(ByteQueue queue, boolean isException){
        if(isException){
            exceptionCode = queue.pop();
        }else{
            readResponse(queue);
        }
    }

    abstract protected void readResponse(ByteQueue queue);

    private static boolean greaterThan(byte b1, byte b2){
        int i1 = b1 & 0xff;
        int i2 = b2 & 0xff;
        return i1 > i2;
    }

    public void validateResponse(ModbusRequest request) throws ModbusTransportException{
        if(getSlaveId() != request.slaveId){
            throw new SlaveIdNotEqual(request.slaveId, getSlaveId());
        }
    }

    public static void main(String[] args) throws Exception{
        ByteQueue queue = new ByteQueue(new byte[] {3, 2});
        ModbusResponse r = createModbusResponse(queue);
        System.out.println(r);
    }
}
