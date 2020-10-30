package code;

import java.math.BigInteger;

import static java.sql.Types.BINARY;

public class DataType {
    public static final int BINARY = 1;
    public static final int TWO_BYTE_INT_UNSIGNED = 2;
    public static final int TWO_BYTE_INT_SIGNED =3;
    public static final int TWO_BYTE_INT_UNSIGNED_SWAPPED = 22;
    public static final int TWO_BYTE_INT_SIGNED_SWAPPED = 23;

    public static final int FOUR_BYTE_INT_UNSIGNED = 4;
    public static final int FOUR_BYTE_INT_SIGNED = 5;
    public static final int FOUR_BYTE_INT_UNSIGNED_SWAPPED = 6;
    public static final int FOUR_BYTE_INT_SIGNED_SWAPPED = 7;
    public static final int FOUR_BYTE_INT_UNSIGNED_SWAPPED_SWAPPED = 24;
    public static final int FOUR_BYTE_INT_SIGNED_SWAPPED_SWAPPED = 25;

    public static final int FOUR_BYTE_FLOAT = 8;
    public static final int FOUR_BYTE_FLOAT_SWAPPED = 9;
    public static final int FOUR_BYTE_FLOAT_SWAPPED_INVERTED = 21;

    public static final int EIGHT_BYTE_INT_UNSIGNED = 10;
    public static final int EIGHT_BYTE_INT_SIGNED = 11;
    public static final int EIGHT_BYTE_INT_UNSIGNED_SWAPPED = 12;
    public static final int EIGHT_BYTE_INT_SIGNED_SWAPPED = 13;
    public static final int EIGHT_BYTE_FLOAT = 14;
    public static final int EIGHT_BYTE_FLOAT_SWAPPED = 15;

    public static final int TWO_BYTE_BCD = 16;
    public static final int FOUR_BYTE_BCD = 17;
    public static final int FOUR_BYTE_BCD_SWAPPED = 20;

    public static final int CHAR = 18;
    public static final int VARCHAR = 19;

    public static final int FOUR_BYTE_MOD_10K = 26;
    public static final int SIX_BYTE_MOD_10K = 27;
    public static final int EIGHT_BYTE_MOD_10K = 28;
    public static final int FOUR_BYTE_MOD_10K_SWAPPED = 29;
    public static final int SIX_BYTE_MOD_10K_SWAPPED = 30;
    public static final int EIGHT_BYTE_MOD_10K_SWAPPED = 31;

    public static final int ONE_BYTE_INT_UNSIGNED_LOWER = 32;
    public static final int ONE_BYTE_INT_UNSIGNED_UPPER = 33;

    public static int getRegisterCount(int id){
        switch (id){
            case BINARY:
            case TWO_BYTE_INT_UNSIGNED:
            case TWO_BYTE_INT_SIGNED:
            case TWO_BYTE_INT_UNSIGNED_SWAPPED:
            case TWO_BYTE_INT_SIGNED_SWAPPED:
            case TWO_BYTE_BCD:
            case ONE_BYTE_INT_UNSIGNED_LOWER:
            case ONE_BYTE_INT_UNSIGNED_UPPER:
                return 1;
            case FOUR_BYTE_INT_UNSIGNED:
            case FOUR_BYTE_INT_SIGNED:
            case FOUR_BYTE_INT_UNSIGNED_SWAPPED:
            case FOUR_BYTE_INT_SIGNED_SWAPPED:
            case FOUR_BYTE_INT_UNSIGNED_SWAPPED_SWAPPED:
            case FOUR_BYTE_INT_SIGNED_SWAPPED_SWAPPED:
            case FOUR_BYTE_FLOAT:
            case FOUR_BYTE_FLOAT_SWAPPED:
            case FOUR_BYTE_BCD:
            case FOUR_BYTE_BCD_SWAPPED:
            case FOUR_BYTE_MOD_10K:
            case FOUR_BYTE_MOD_10K_SWAPPED:
                return 2;
            case SIX_BYTE_MOD_10K:
            case SIX_BYTE_MOD_10K_SWAPPED:
                return 3;
            case EIGHT_BYTE_INT_UNSIGNED:
            case EIGHT_BYTE_INT_SIGNED:
            case EIGHT_BYTE_INT_UNSIGNED_SWAPPED:
            case EIGHT_BYTE_INT_SIGNED_SWAPPED:
            case EIGHT_BYTE_FLOAT:
            case EIGHT_BYTE_FLOAT_SWAPPED:
            case EIGHT_BYTE_MOD_10K:
            case EIGHT_BYTE_MOD_10K_SWAPPED:
                return 4;
        }
        return 0;
    }
    public static Class<?> getJavaType(int id){
        switch (id){
            case ONE_BYTE_INT_UNSIGNED_LOWER:
            case ONE_BYTE_INT_UNSIGNED_UPPER:
                return Integer.class;
            case BINARY:
                return Boolean.class;
            case TWO_BYTE_INT_UNSIGNED:
            case TWO_BYTE_INT_UNSIGNED_SWAPPED:
                return Integer.class;
            case TWO_BYTE_INT_SIGNED:
            case TWO_BYTE_INT_SIGNED_SWAPPED:
                return Short.class;
            case FOUR_BYTE_INT_UNSIGNED:
                return Long.class;
            case FOUR_BYTE_INT_SIGNED:
                return Integer.class;
            case FOUR_BYTE_INT_UNSIGNED_SWAPPED:
            case FOUR_BYTE_INT_UNSIGNED_SWAPPED_SWAPPED:
                return Long.class;
            case FOUR_BYTE_INT_SIGNED_SWAPPED:
            case FOUR_BYTE_INT_SIGNED_SWAPPED_SWAPPED:
                return Integer.class;
            case FOUR_BYTE_FLOAT:
                return Float.class;
            case FOUR_BYTE_FLOAT_SWAPPED:
                return Float.class;
            case FOUR_BYTE_FLOAT_SWAPPED_INVERTED:
                return Float.class;
            case EIGHT_BYTE_INT_UNSIGNED:
                return BigInteger.class;
            case EIGHT_BYTE_INT_SIGNED:
                return Long.class;
            case EIGHT_BYTE_INT_UNSIGNED_SWAPPED:
                return BigInteger.class;
            case EIGHT_BYTE_INT_SIGNED_SWAPPED:
                return Long.class;
            case EIGHT_BYTE_FLOAT:
                return Double.class;
            case EIGHT_BYTE_FLOAT_SWAPPED:
                return Double.class;
            case TWO_BYTE_BCD:
                return Short.class;
            case FOUR_BYTE_BCD:
            case FOUR_BYTE_BCD_SWAPPED:
                return Integer.class;
            case CHAR:
            case VARCHAR:
                return String.class;
            case FOUR_BYTE_MOD_10K:
            case SIX_BYTE_MOD_10K:
            case EIGHT_BYTE_MOD_10K:
            case FOUR_BYTE_MOD_10K_SWAPPED:
            case SIX_BYTE_MOD_10K_SWAPPED:
            case EIGHT_BYTE_MOD_10K_SWAPPED:
                return BigInteger.class;
        }
        return null;
    }
}
