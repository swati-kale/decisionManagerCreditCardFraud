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

            URL url = new URL("https://rhpam7-fraud-detection-kieserver-rhpam7-fraud-detection.apps.mw-ocp4.cloud.lab.eng.bos.redhat.com/services/rest/server/containers/test-case-project_1.0.0/cases/src.fraudWorkflow/instances");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization","Basic YWRtaW5Vc2VyOnRlc3QxMjM0IQ==");

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



            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }



}
