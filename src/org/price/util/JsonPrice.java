package org.price.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import java.util.UUID;
import java.util.stream.Collectors;

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
		//String json = getJsonInURL(url);
		//System.out.println(json);
		
		URL URL = new URL(url);
		
		System.out.println(getJson(URL.openStream()));
		
		//KeyValue.processJson(json);
		

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
	
	public static String getJson(InputStream is) {
		 String text = null;
		 Path tempFile;
		try {
			 tempFile = Files.createTempDirectory("").resolve(UUID.randomUUID().toString() + ".tmp");
			 Files.copy(is, tempFile, StandardCopyOption.REPLACE_EXISTING);
			 text = new String(Files.readAllBytes(tempFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		    
		return text;    
	}
	
	
	public String convert(InputStream inputStream, Charset charset) throws IOException {
		
		charset = StandardCharsets.UTF_8;
		 
		StringBuilder stringBuilder = new StringBuilder();
		String line = null;
		
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, charset))) {	
			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line);
			}
		}
	 
		return stringBuilder.toString();
	}
	
	public String convert(InputStream inputStream) throws IOException {
		
		try (Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name())) {
			return scanner.useDelimiter("\\A").next();
		}
	}
	
	public String convertToStream(InputStream inputStream, Charset charset) throws IOException {
		
		charset = StandardCharsets.UTF_8;
		 
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, charset))) {
			return br.lines().collect(Collectors.joining(System.lineSeparator()));
		}
	}

	

}
