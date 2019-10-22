package org.price.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class JsonPrice {
	
	private static final String KEY = "GGGI9KHP5G92EMK3";
	private static final String TIME_SERIES_INTRADAY = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=";

	public static void main(String[] args) throws IOException {
		//Required: function = The time series of your choice. In this case, function=TIME_SERIES_INTRADAY
		//TIME_SERIES_DAILY
		String function = "TIME_SERIES_INTRADAY"; 
		//Required: symbol = The name of the equity of your choice. For example: symbol=MSFT
		String symbol = "MSFT";
		//Required: interval = Time interval between two consecutive data points in the time series. The following values are supported: 1min, 5min, 15min, 30min, 60min
		String interval = "5min";
		//Optional: outputsize = By default, outputsize=compact. Strings compact and full are accepted with the following specifications: 
		//compact returns only the latest 100 data points in the intraday time series; full returns the full-length intraday time series. 
		//The "compact" option is recommended if you would like to reduce the data size of each API call.
		String outputsize = "compact";
		//Optional: datatype = By default, datatype=json. Strings json and csv are accepted with the following specifications: json returns the intraday time series in JSON format; csv returns the time series as a CSV (comma separated value) file.
		//https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=5min&apikey=demo&datatype=csv
		String datatype = "json";
		
		String url = TIME_SERIES_INTRADAY+symbol+"&interval="+interval+"&apikey="+KEY;
		
		//String url = "https://www.alphavantage.co/query?function=SYMBOL_SEARCH&keywords=BA&apikey=GGGI9KHP5G92EMK3";
		String json = getJsonInURL(url);
		System.out.println(json);

	}
	
	public static String getJsonInURL(String url) throws IOException {
		
		String s = "";
		
		BufferedReader reader = null;
	    try {
	        URL URL = new URL(url);
	        reader = new BufferedReader(new InputStreamReader(URL.openStream()));
	        StringBuffer buffer = new StringBuffer();
	        int read;
	        char[] chars = new char[1024];
	        while ((read = reader.read(chars)) != -1) {
	            buffer.append(chars, 0, read); 
	            //System.out.println(buffer.toString());
	            s+=buffer.toString();
	        }

	        
	    } finally {
	        if (reader != null)
	            reader.close();
	    }
	    return s;	
	}

}
