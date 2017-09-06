package com.company;

import org.json.JSONObject;
import org.json.XML;

import java.io.BufferedReader;
import java.io.FileReader;

public class Main {

    public static void main(String[] args) {
        //System.out.println(xmlToJson("/Users/cesar/Metlife/Nist de ejemplo/RT_CAFP900909MMCLLR87.xml"));
        //System.out.println(xmlToJson("/Users/cesar/Metlife/Nist de ejemplo/CAFG700909MMCLLR30.xml"));
        //System.out.println(xmlToJson("/Users/cesar/Metlife/Nist de ejemplo/CAFZ700909MMCLLR10.xml"));
        //System.out.println(xmlToJson("/Users/cesar/Metlife/Nist de ejemplo/TIOE830525MHGRRD01.xml"));
        System.out.println(xmlToJson("/Users/cesar/Metlife/Nist de ejemplo/BOHB751124MMCSRL06.xml"));
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
}