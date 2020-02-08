package com.passgen;

import java.io.*;
import java.util.*;
import javax.net.ssl.HttpsURLConnection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.net.*;
import org.json.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class userlistparser {
    public String kilit;
    public String vkn1;
    public String alias;
    private static final String EMPTY_STRING = "";

    public void jsonyaz1() throws IOException {
 
        userlistreq userlist = new userlistreq();
        userlist.setInputType("JSON");
        userlist.setInputTransferType("STRING");
        userlist.setReturnType("JSON");
        userlist.setReturnTransferType("OBJECT");
        userlist.setInputData(vkn1);
        Gson gsonBuilder13 = new GsonBuilder().disableHtmlEscaping().create();
        String getreq23 = gsonBuilder13.toJson(userlist);
        getreq23.replace("\\\\", "\\");

    try{
        
        //Credentials için UserName and Password değerlerimi giriyorum.
        String user_name="admin";
        String password="Admin123";

        String auth=new StringBuffer(user_name).append(":").append(password).toString();
        String url2 = "http://192.168.1.75/api/invoice/userlist";
        HttpPost post = new HttpPost(url2);

        URL obj = new URL(null,url2,new sun.net.www.protocol.https.Handler());
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        con.setRequestMethod("POST");

        String basicAuth ="Basic " + new String(Base64.getEncoder().encodeToString(auth.getBytes()));
        post.setHeader("Authorization", basicAuth);
        post.setHeader("Content-Type", "application/json");
        post.setHeader("Accept", "application/json");
        post.setHeader("X-Stream" , "true");
    
        post.setEntity(new StringEntity(getreq23));
      
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = client.execute(post);
        JSONObject result1 = new JSONObject(EntityUtils.toString(response.getEntity()));


         Boolean success = (Boolean) result1.get("success");

         if (success==false){
            kilit ="kilitli";
            try{
            System.out.println("********************************************\n");
            System.out.println("Userlist 'te böyle bir kullanıcı bulunamadı. Lütfen mail girerek kullanıcı oluşturun..'");
            Scanner scanner = new Scanner(System.in);
            System.out.println("\n'mail:urn' giriniz : ");
            String aliasgirilen = scanner.nextLine();
           
                alias=aliasgirilen; 

                kilit ="";
                
              // System.out.println("urn:mail ile başlayan bir mail girin.");
              
           
        }
        catch(Exception e){
            System.out.println(e);
        }

		}
        // String titlestr = title.getJSONObject(0).getString("title");
        
        // Gelen Json Response' dan Şirketin Mail Adresine Erişmek için Katman Katman Jsonu Açıyorum ve En Derinden NAME Arrayini Alıyorum
        else {
        JSONArray denemearray=result1.getJSONObject("data").getJSONArray("user");
        JSONObject deneme0=denemearray.getJSONObject(0);
        JSONArray fellowarray =deneme0.getJSONObject("documents").getJSONArray("document");
        JSONObject fallowarray0=fellowarray.getJSONObject(0);
        JSONArray array2 = fallowarray0.getJSONArray("alias");
        JSONArray deneme = array2.getJSONObject(0).getJSONArray("name");

        // Array' i ToString Yapıyorum, Çünkü Gelen Value' da İstemediğimiz Bazı Karakterler Var O Yüzden REPLACE Ediyorum. 
        String denemestr = deneme.toString();
        String denemereplace1=denemestr.replace("[",EMPTY_STRING).replace("]",EMPTY_STRING).replace("pk", "gb").replace("\"", "");

        alias = denemereplace1;
        }
   } 
   catch (Exception e) {
    System.out.println("Hata:"+e);
   }
    
}



}