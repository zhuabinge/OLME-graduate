package com.olme.domain;

/**
 * Created by Bingo on 2014/8/13.
 */
public class VedioInfo {
    private int vedioId;
    private int chapterId;
    private String vedioName;
    private String vedioLocation;
    private int vedioLength;
    private int vedioOpenLength;

    public VedioInfo() {
        super();
    }

    public VedioInfo(int vedioId, int chapterId, String vedioName, String vedioLocation, int vedioLength, int vedioOpenLength) {
        this.vedioId = vedioId;
        this.chapterId = chapterId;
        this.vedioName = vedioName;
        this.vedioLocation = vedioLocation;
        this.vedioLength = vedioLength;
        this.vedioOpenLength = vedioOpenLength;
    }

    public int getVedioId() {
        return vedioId;
    }

    public void setVedioId(int vedioId) {
        this.vedioId = vedioId;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public String getVedioName() {
        return vedioName;
    }

    public void setVedioName(String vedioName) {
        this.vedioName = vedioName;
    }

    public String getVedioLocation() {
        return vedioLocation;
    }

    public void setVedioLocation(String vedioLocation) {
        this.vedioLocation = vedioLocation;
    }

    public int getVedioLength() {
        return vedioLength;
    }

    public void setVedioLength(int vedioLength) {
        this.vedioLength = vedioLength;
    }

    public int getVedioOpenLength() {
        return vedioOpenLength;
    }

    public void setVedioOpenLength(int vedioOpenLength) {
        this.vedioOpenLength = vedioOpenLength;
    }

	@Override
	public String toString() {
		return "VedioInfo [vedioId=" + vedioId + ", chapterId=" + chapterId
				+ ", vedioName=" + vedioName + ", vedioLocation="
				+ vedioLocation + ", vedioLength=" + vedioLength
				+ ", vedioOpenLength=" + vedioOpenLength + "]";
	}
    
}
