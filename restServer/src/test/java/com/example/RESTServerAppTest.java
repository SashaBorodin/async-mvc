package com.example;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.springframework.http.HttpHeaders.USER_AGENT;

/**
 * Created by Alexander on 17.03.2017.
 */
public class RESTServerAppTest {

    static int USERS_COUNT = 2000;

    static CountDownLatch startLatch = new CountDownLatch(1);
    static CountDownLatch finishLatch = new CountDownLatch(USERS_COUNT);

    @Ignore
    @Test
    public void test_200_clients() throws Exception {

//        System.setProperty("java.net.preferIPv4Stack", "true");

        for (int i = 0; i < USERS_COUNT; i++) {
            new GETSender().start();
        }

        startLatch.countDown();

        System.out.println("all started!");

        finishLatch.await();
    }

    class GETSender extends Thread {

        @Override
        public void run() {

            try {

                startLatch.await();

//                System.out.println("started");

                String url = "http://localhost:8080/callable";

                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                // optional default is GET
                con.setRequestMethod("GET");

                //add request header
                con.setRequestProperty("User-Agent", USER_AGENT);

                int responseCode = con.getResponseCode();
//                System.out.println("\nSending 'GET' request to URL : " + url);
//                System.out.println("Response Code : " + responseCode);

                Assert.assertEquals(200, responseCode);

                finishLatch.countDown();
/*
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
*/
                //print result
//                System.out.println(response.toString());

            } catch (Exception e) {
                System.out.println("FAILED");
            }
        }
    }


}