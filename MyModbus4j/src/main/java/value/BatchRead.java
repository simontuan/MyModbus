package value;

import base.ReadFunctionGroup;

import java.util.List;

public class BatchRead<K> {
    private final List<KeyedModbusLocator<K>> requestValues = new ArrayList<>();

    private boolean contiguousRequests = false;

    private boolean errorsInResults = false;

    private boolean excepitionsInResults = false;

    private boolean cancel;

    private List<ReadFunctionGroup<K>> functionGroups;

    public boolean isContiguousRequests(){
        return contiguousRequests;
    }
    public void setContiguousRequests(boolean contiguousRequests){
        this.contiguousRequests = contiguousRequests;
        functionGroups = null;
    }
    public boolean isErrorsInResults(){
        return errorsInResults;
    }
    public void setErrorsInResults(boolean errorsInResults){
        this.errorsInResults = errorsInResults;
    }
    public boolean isExcepitionsInResults(){
        return excepitionsInResults;
    }
    public void setExcepitionsInResults(boolean excepitionsInResults){
        this.excepitionsInResults = excepitionsInResults;
    }
    public List<ReadFunctionGroup<K>> getFunctionGroups(ModbusMaster master){
        if(functionGroups == null){
            doPartition(master);
        }
        return functionGroups;
    }
    public void addLocator(K id, BaseLocator<?> locator){
        addLocator(new KeyedModbusLocator<>(id, locator));
    }
    private void addLocator(KeyedModbusLocator<K> locator){
        requestValues.add(locator);
        functionGroups = null;
    }
    public void setCancel(boolean cancel){
        this.cancel = cancel;
    }
    private void doPartition(ModbusMaster master){
        Map<SlaveAndRange, List<KeyedModbusLocator<K>>> slaveRangeBatch = new HashMap<>();

        List<KeyedModbusLocator<K>> functionList;
        for(KeyedModbusLocator<K> locator : requestValues){
            functionList = slaveRangeBatch.get(locator.getSlaveAndRange());
            if(functionList == null){
                functionList - new ArrayList<>();
                slaveRangeBatch.put(locator.getSlaveAndRange(), functionList);
            }
            functionList.add(locator);
        }
        Collection<List<KeyedModbusLocator<K>>> fucntionLocatorLists = slaveRangeBathc.values();
        FunctionLocatorComparator comparator = new FunctionLocatorComparator();
        functionGroups = new ArrayList<>();
        for(List<KeyedModbusLocator<K>> functionLocatorList : functionLocatorLists){
            Collections.sort(functionLocatorList, comparator);
            int maxReadCount = master.getMaxReadCount(functionLocatorList.get(0).getSlaveAndRange().getRange());
            createRequestGroups(functionGroups, functionLocatorList, maxReadCount)
        }
    }

    private void createRequestGroups(List<ReadFunctionGroup<K>> functionGroups, List<KeyedModbusLocator<K>> locators,
                                     int maxCount){
        ReadFunctionGroup<K> functionGroup;
        KeyedModbusLocator<K> locator;
        int index;
        int endOffset;
        while(locators.size() > 0){
            functionGroup = new ReadFucntionGroup<>(locator.remove(0));
            functionGroups.add(functionGroup);
            endOffset = functionGroup.getStartOffset() + maxCount -1;

            index = 0;
            while(locator.size() > index){
                locator = locators.get(index);
                boolean added = false;
                if(locator.getEndOffset() > endOffset){
                    break;
                }
                index++;
            }
        }
    }
    class FunctionLocatorComparator implements Comparator<KeyedModbusLocator<K>>{
        @Override
        public int compare(KeyedModbusLocatror<K> ml1, KeyedModbusLocator<K> ml2){
            return ml1.getOffset() - ml2.getOffset();
        }
    }
}

