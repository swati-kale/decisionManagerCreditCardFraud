package com.redhat.demo.dm.ccfraud;



import com.google.gson.Gson;
import com.redhat.demo.dm.ccfraud.domain.*;


import java.io.IOException;
import java.io.OutputStream;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

public class CaseMgmt {


    public void invokeCase(PotentialFraudFact potentialFraudFact) {

        try {
            System.out.println("Start-------------Case Management Invoke---------");
            URL url = new URL("http://rhpam7-fraud-detection-kieserver-http-rhpam7-fraud-detection.apps.mw-ocp4.cloud.lab.eng.bos.redhat.com/services/ress/test-case-project_1.0.0/cases/src.fraudWorkflow/instances");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization","Basic YWRtaW5Vc2VyOlJlZEhhdA==");
             System.out.println("KIE_SERVICER Connection::::::::"+conn);
             PotentialFraudFactCaseFile potentialFraudFactCaseFile = new PotentialFraudFactCaseFile(String.valueOf(potentialFraudFact.getCreditCardNumber()),potentialFraudFact.getTransactions().toString());
            String transactionList = "";

            for(CreditCardTransaction transaction:potentialFraudFact.getTransactions()) {
                if(transactionList.trim().isEmpty()) {
                transactionList = transaction.getTransactionNumber() + "";
                } else {
                    transactionList = "," + transaction.getTransactionNumber();
                }
            }

            potentialFraudFactCaseFile.setCaseFile_transactionList(transactionList);

            OutputStream os = conn.getOutputStream();

            os.write(new Gson().toJson(potentialFraudFactCaseFile).getBytes());

            os.flush();


            System.out.println("KIE_SERVICER Connection ResponseCode"+ conn.getResponseCode());
            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
            System.out.println("KIE_SERVICER Connection HTTP error code "+ conn.getResponseCode());
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());

            }

            conn.disconnect();
            System.out.println("END-------------Case Management Invoke---------");

        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException-------------Case Management Invoke---------");
            e.printStackTrace();

        } catch (IOException e) {
            System.out.println("IOException-------------Case Management Invoke---------");
            e.printStackTrace();

        }

    }



}
