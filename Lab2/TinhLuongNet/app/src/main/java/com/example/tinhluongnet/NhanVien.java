package com.example.tinhluongnet;

public class NhanVien {
    private String HoTen;
    private long LuongGross;

    public void setHoTen(String hoten) {
        HoTen = hoten;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setLuongGross(long luonggross) {
        LuongGross = luonggross;
    }

    public long getLuongGross() {
        return LuongGross;
    }

    public long LuongNet() {
        long a = (long) (LuongGross - LuongGross * 0.105);
        if(a <= 11000000)
        {
            return a;
        }
        else
        {
            return (long) (LuongGross - LuongGross * 0.105 - (a - 11000000) * 0.05);
        }
    }

    public String toString() {
        return "Họ tên: " + getHoTen() + "\n" + "Lương net: " + LuongNet();
    }
}
