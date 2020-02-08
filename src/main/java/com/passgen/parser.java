package com.passgen;

import java.io.*;
import java.util.*;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.net.*;
import org.json.*;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
public class parser {

    public String vkn1;
    public String sifre;
    public String base4;
    public String url1;
    public void pars() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.toString();
    }
    public void jsonyaz(String alias) throws IOException {
        
        String mail1 = alias;
        getvkn getvkn1 = new getvkn();
        getvkn1.setCompanyId(vkn1);
        getvkn1.setPassword(sifre);
        getvkn1.setUserName(vkn1);
        getvkn1.setAlias(mail1);
        getvkn1.setRoles("USER,VIEW,earchive");
        Gson gsonBuilder = new GsonBuilder().create();
        String deneme5 = gsonBuilder.toJson(getvkn1);
        String encodedBytes = Base64.getEncoder().encodeToString(deneme5.getBytes());
        getreq getreq1 = new getreq();
        getreq1.setInputType("JSON");
        getreq1.setInputTransferType("BASE64");
        getreq1.setReturnType("JSON");
        getreq1.setReturnTransferType("OBJECT");
        getreq1.setInputData(encodedBytes);
        Gson gsonBuilder1 = new GsonBuilder().disableHtmlEscaping().create();
        String getreq2 = gsonBuilder1.toJson(getreq1);
        getreq2.replace("\\\\", "\\");
          
        String user_name="admin";
        String password="admin123!";

        String auth=new StringBuffer(user_name).append(":").append(password).toString();
        HttpPost post = new HttpPost(url1);

        URL obj = new URL(null,url1,new sun.net.www.protocol.https.Handler());
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        con.setRequestMethod("POST");

        String basicAuth ="Basic " + new String(Base64.getEncoder().encodeToString(auth.getBytes()));
        post.setHeader("Authorization", basicAuth);
        post.setHeader("Content-Type", "application/json");
        post.setHeader("Accept", "application/json");
        post.setHeader("X-Stream" , "true");
    
        post.setEntity(new StringEntity(getreq2));
        
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = client.execute(post);
        JSONObject result1 = new JSONObject(EntityUtils.toString(response.getEntity()));
        System.out.println("*************************************\n"+vkn1+" VKN Numaralı Kullanıcı Oluşturuldu."+"\nŞifre : "+sifre);
		StringSelection selection = new StringSelection(sifre);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, selection);
   
    
}

public void setUrl(String url)
{
    url1=url;
}

public String getUrl()
{
    return url1;
}


}