package com.tingtingfm.ttsdk.net;

public class Response
{
	private int erron;
	private String error;
	private String data;

    public boolean hasError() {
        return erron != 0 ? true : false;
    }

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getErron() {
		return erron;
	}

	public void setErron(int erron) {
		this.erron = erron;
	}


}
