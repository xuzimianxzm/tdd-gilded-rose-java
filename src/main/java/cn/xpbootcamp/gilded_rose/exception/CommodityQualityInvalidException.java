package cn.xpbootcamp.gilded_rose.exception;

public class CommodityQualityInvalidException extends RuntimeException {
    public CommodityQualityInvalidException() {
        super("The value of the product cannot be less than 0 and never exceed 50!");
    }
}
