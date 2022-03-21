package com.example.demo.entity;

public class ExcelSheet3 {
    private String account;
    private String vendor;
    private String blk1;
    private double mtdActual;
    private double lYMtdActual;
    private double changeVsLY;
    private String blk2;
    private double ytdActual;
    private double lYYtdActual;
    private double changeVsLY1;
    private String blk3;
    private double jan;
    private double feb;
    private double mar;
    private double apr;
    private double may;
    private double jun;
    private double jul;
    private double aug;
    private double sep;
    private double oct;
    private double nov;
    private double dec;
    private double ytdActual1;

    public ExcelSheet3() {
    }

    public ExcelSheet3(String vendor, String account, String blk1, double mtdActual, double lYMtdActual, double changeVsLY, String blk2, double ytdActual, double lYYtdActual, double changeVsLY1, String blk3, double jan, double feb, double mar, double apr, double may, double jun, double jul, double aug, double sep, double oct, double nov, double dec, double ytdActual1) {
        this.account = account;
        this.vendor = vendor;
        this.blk1 = blk1;
        this.mtdActual = mtdActual;
        this.lYMtdActual = lYMtdActual;
        this.changeVsLY = changeVsLY;
        this.blk2 = blk2;
        this.ytdActual = ytdActual;
        this.lYYtdActual = lYYtdActual;
        this.changeVsLY1 = changeVsLY1;
        this.blk3 = blk3;
        this.jan = jan;
        this.feb = feb;
        this.mar = mar;
        this.apr = apr;
        this.may = may;
        this.jun = jun;
        this.jul = jul;
        this.aug = aug;
        this.sep = sep;
        this.oct = oct;
        this.nov = nov;
        this.dec = dec;
        this.ytdActual1 = ytdActual1;
    }

    @Override
    public String toString() {
        return "ExcelSheet3{" +
                "account='" + account + '\'' +
                ", vendor='" + vendor + '\'' +
                ", blk1='" + blk1 + '\'' +
                ", mtdActual=" + mtdActual +
                ", lYMtdActual=" + lYMtdActual +
                ", changeVsLY=" + changeVsLY +
                ", blk2='" + blk2 + '\'' +
                ", ytdActual=" + ytdActual +
                ", lYYtdActual=" + lYYtdActual +
                ", changeVsLY1=" + changeVsLY1 +
                ", blk3='" + blk3 + '\'' +
                ", jan=" + jan +
                ", feb=" + feb +
                ", mar=" + mar +
                ", apr=" + apr +
                ", may=" + may +
                ", jun=" + jun +
                ", jul=" + jul +
                ", aug=" + aug +
                ", sep=" + sep +
                ", oct=" + oct +
                ", nov=" + nov +
                ", dec=" + dec +
                ", ytdActual1=" + ytdActual1 +
                '}';
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getBlk1() {
        return blk1;
    }

    public void setBlk1(String blk1) {
        this.blk1 = blk1;
    }

    public double getMtdActual() {
        return mtdActual;
    }

    public void setMtdActual(double mtdActual) {
        this.mtdActual = mtdActual;
    }

    public double getlYMtdActual() {
        return lYMtdActual;
    }

    public void setlYMtdActual(double lYMtdActual) {
        this.lYMtdActual = lYMtdActual;
    }

    public double getChangeVsLY() {
        return changeVsLY;
    }

    public void setChangeVsLY(double changeVsLY) {
        this.changeVsLY = changeVsLY;
    }

    public String getBlk2() {
        return blk2;
    }

    public void setBlk2(String blk2) {
        this.blk2 = blk2;
    }

    public double getYtdActual() {
        return ytdActual;
    }

    public void setYtdActual(double ytdActual) {
        this.ytdActual = ytdActual;
    }

    public double getlYYtdActual() {
        return lYYtdActual;
    }

    public void setlYYtdActual(double lYYtdActual) {
        this.lYYtdActual = lYYtdActual;
    }

    public double getChangeVsLY1() {
        return changeVsLY1;
    }

    public void setChangeVsLY1(double changeVsLY1) {
        this.changeVsLY1 = changeVsLY1;
    }

    public String getBlk3() {
        return blk3;
    }

    public void setBlk3(String blk3) {
        this.blk3 = blk3;
    }

    public double getJan() {
        return jan;
    }

    public void setJan(double jan) {
        this.jan = jan;
    }

    public double getFeb() {
        return feb;
    }

    public void setFeb(double feb) {
        this.feb = feb;
    }

    public double getMar() {
        return mar;
    }

    public void setMar(double mar) {
        this.mar = mar;
    }

    public double getApr() {
        return apr;
    }

    public void setApr(double apr) {
        this.apr = apr;
    }

    public double getMay() {
        return may;
    }

    public void setMay(double may) {
        this.may = may;
    }

    public double getJun() {
        return jun;
    }

    public void setJun(double jun) {
        this.jun = jun;
    }

    public double getJul() {
        return jul;
    }

    public void setJul(double jul) {
        this.jul = jul;
    }

    public double getAug() {
        return aug;
    }

    public void setAug(double aug) {
        this.aug = aug;
    }

    public double getSep() {
        return sep;
    }

    public void setSep(double sep) {
        this.sep = sep;
    }

    public double getOct() {
        return oct;
    }

    public void setOct(double oct) {
        this.oct = oct;
    }

    public double getNov() {
        return nov;
    }

    public void setNov(double nov) {
        this.nov = nov;
    }

    public double getDec() {
        return dec;
    }

    public void setDec(double dec) {
        this.dec = dec;
    }

    public double getYtdActual1() {
        return ytdActual1;
    }

    public void setYtdActual1(double ytdActual1) {
        this.ytdActual1 = ytdActual1;
    }
}
