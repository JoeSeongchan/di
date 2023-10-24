package web.model.vo;

import java.io.Serializable;

public class RegionVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private String addr1;
    private String addr2;
    private String zipCode;
    private String firstImage;
    private double latitude;
    private double longitude;

    public RegionVO() {
        super();
    }

    public RegionVO(String title, String addr1, String addr2, String zipCode, String firstImage, double latitude,
                    double longitude) {
        setTitle(title);
        setAddr1(addr1);
        setAddr2(addr2);
        setZipCode(zipCode);
        setFirstImage(firstImage);
        setLatitude(latitude);
        setLongitude(longitude);
    }
    // TODO: setter에 유효성 검증 코드 넣기 
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public String getAddr2() {
        return addr2;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getFirstImage() {
        return firstImage;
    }

    public void setFirstImage(String firstImage) {
        this.firstImage = firstImage;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}
