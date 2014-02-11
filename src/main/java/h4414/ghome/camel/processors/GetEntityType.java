/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package h4414.ghome.camel.processors;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.http.NameValuePair;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.commons.httpclient.URIException;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;

/**
 *
 * @author Mathis
 */
public class GetEntityType implements Processor {
    public void process( Exchange exchange ){
        String url = exchange.getIn().getHeader(Exchange.HTTP_URL, String.class);
        
            String httpQ = exchange.getIn().getHeader(Exchange.HTTP_QUERY, String.class);
            
            
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            
            String[] kv = httpQ.split("&");
            String [] keyAndVals = new String[kv.length*2];
            for ( int j = 0 ; j < kv.length ; j++ ){
                String[] sa = kv[j].split("=");
                params.add ( new BasicNameValuePair(sa[0],sa[1]));
            }
            
            
            //List<NameValuePair> params = URLEncodedUtils.parse(exchange.getIn().getHeader(Exchange.HTTP_QUERY, String.class), Charset.defaultCharset());
            Iterator<NameValuePair> it = params.iterator();
            System.out.println("nvp : "+params);
            while ( it.hasNext()){
                NameValuePair nvp = it.next();
                switch ( nvp.getName()){
                    case "name" :{
                        exchange.setProperty("entityName", nvp.getValue());
                        
                        break;
                    }
                    case "nb" :{
                        exchange.setProperty("nbEntity", nvp.getValue());
                        break;
                    }
                }
                if ( exchange.getProperty("entityName",String.class) != null ){
                    exchange.getIn().setHeader("go",true);
                }
                else{
                    exchange.getIn().setHeader("go", false);
                }
            }
        } 
        
        
    
    
}
