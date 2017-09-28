package com.company;

import org.json.JSONObject;
import org.json.XML;

import java.io.*;
import java.util.Base64;

public class Main {

    public static void main(String[] args) {
        //System.out.println(xmlToJson("/Users/cesar/Metlife/Nist de ejemplo/RT_CAFP900909MMCLLR87.xml"));
        //System.out.println(xmlToJson("/Users/cesar/Metlife/Nist de ejemplo/CAFG700909MMCLLR30.xml"));
        //System.out.println(xmlToJson("/Users/cesar/Metlife/Nist de ejemplo/CAFZ700909MMCLLR10.xml"));
        //System.out.println(xmlToJson("/Users/cesar/Metlife/Nist de ejemplo/TIOE830525MHGRRD01.xml"));
        //System.out.println(xmlToJson("/Users/cesar/Metlife/Nist de ejemplo/pruebas_edgar_13092017/OIGC810619HDFRLR01.xml"));
        //System.out.println(xmlToJson("/Users/cesar/Metlife/Nist de ejemplo/pruebas_edgar_13092017/LIFL571031HGTNRS07[1].xml"));
        //System.out.println(splitDate("2017-09-25")[0]+ splitDate("2017-09-25")[1]+ splitDate("2017-09-25")[2]);
        //System.out.println(xmlToJson("/Users/cesar/Documents/nist_paco.xml"));
        System.out.println(decode64(readFile("/Users/cesar/Metlife/Nist de ejemplo/nist.txt")));
    }

    public static String[] splitDate(String s) {
        return s.split("-");
    }

    public static JSONObject xmlToJson(String path){

        JSONObject jsonObject=new JSONObject();
        String line="",str="";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                str += line;
            }
            jsonObject = NistXmlParser.parse(XML.toJSONObject(str));
        }catch (Exception e){

            System.out.println("Xml2json: "+e.getMessage());

        }

        return jsonObject;
    }

    public static String decode64(String s) {
        String result = "";
        // Decode
        byte[] base64decodedBytes = Base64.getDecoder().decode(s);
        try {
            result = new String(base64decodedBytes, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("Original String: " + result);

        return result;
    }

    public static String readFile(String filename)
    {
        String content = null;
        File file = new File(filename); //for ex foo.txt
        FileReader reader = null;
        try {
            reader = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            content = new String(chars);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(reader !=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.replaceAll("\n", "");
    }
}