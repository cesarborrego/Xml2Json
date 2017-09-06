package com.company;

import org.json.JSONArray;
import org.json.JSONObject;

public class json {

    //KeyService
    public static final String KS_RH_SEMILLAS="0201";
    public static final String KS_RH_MASIVO="0202";
    public static final String KS_RH_RECURRENTE="0203";
    public static final String KS_RH_REGISTRO="0301";

    //KeyModule
    public static final String MODULE_RM="RM";
    public static final String MODULE_RR="RR";
    public static final String MODULE_RT="RT";
    public static final String MODULE_MD="MD";
    public static final String MODULE_RP="RP";
    public static final String MODULE_PM="PM";

    //Keys
    public static final String KEY_SERVICE = "clave_servicio";
    public static final String MODULE = "modulo";
    public static final String ENRROLLED_CURP = "curp_enrolado";
    public static final String ENRROLLER_CURP = "curp_enrolador";
    public static final String SIGNATURE_DATE = "fecha_firma";
    public static final String DELIVERY_DATE = "DeliveryDate";
    public static final String KEY_ENROLL_AFORE = "KeyEnrollAfore";
    public static final String PERSON_ENROLLED_TYPE = "TypePersonEnrolled";
    public static final String PERSON_ENROLLER_TYPE = "TypePersonEnroller";
    public static final String FINGERPRINT = "Fingerprint";
    public static final String IMAGE_BITS_PP_QUANTITY = "ImageBitsPPQuantity";
    public static final String CAPTURE_DATE = "CaptureDate";
    public static final String IDENTIFICATION_ID = "IdentificationID";
    public static final String CAPTURE_RESOLUTION = "CaptureResolution";
    public static final String IMAGE_COMPRESSION_ALGORITHM = "ImageCompressionAlgorithm";
    public static final String IMAGE_SCALE_UNITS_CODE = "ImageScaleUnitsCode";
    public static final String NIST_QUALITY_MEASURE = "NISTQualityMeasure";
    public static final String FINGERPRINT_IMAGE_ACQUISITION = "FingerprintImageAcquisition";
    public static final String FINGER_MISS_CODE = "FingerMissCode";

    //Json xml keys
    public static final String NISTBiometricInformation = "itl:NISTBiometricInformationExchangePackage";
    public static final String PackageFingerprint= "itl:PackageFingerprintImageRecord";
    public static final String PackageDescriptiveTextRecord = "itl:PackageDescriptiveTextRecord";
    public static final String DatosEnrolTrabajador = "itl:DatosEnrolTrabajador";

    public static final String curpTrabajador = "prc:curpTrabajador";
    public static final String curpEmpleado = "prc:curpEmpleado";
    public static final String fechaFirma = "prc:fechaFirma";


    public static JSONObject parse(JSONObject xml){

        JSONObject json = new JSONObject();
        JSONArray fingers = new JSONArray();
        JSONObject fingerObject = new JSONObject();

        try {
            //Clave Servicio
            //Modulo
            //curp_enrolado
            json.put(ENRROLLED_CURP, xml.optJSONObject(NISTBiometricInformation)
                    .optJSONObject(PackageDescriptiveTextRecord)
                    .optJSONObject(DatosEnrolTrabajador)
                    .optString(curpTrabajador));
            //curp_enrolador
            json.put(ENRROLLER_CURP, xml.optJSONObject(NISTBiometricInformation)
                    .optJSONObject(PackageDescriptiveTextRecord)
                    .optJSONObject(DatosEnrolTrabajador)
                    .optString(curpEmpleado));
            //fecha_firma
            json.put(SIGNATURE_DATE, xml.optJSONObject(NISTBiometricInformation)
                    .optJSONObject(PackageDescriptiveTextRecord)
                    .optJSONObject(DatosEnrolTrabajador)
                    .optString(fechaFirma));
            //DeliveryDate
            //KeyEnrollAfore
            //TypePersonEnrolled
            //TypePersonEnroller
            //Fingerprint
            //ImageBitsPPQuantity
            //CaptureDate
            //IdentificationID
            //CaptureResolution
            //ImageCompressionAlgorithm
            //ImageScaleUnitsCode
            //NISTQualityMeasure
            //FingerprintImageAcquisition
            //FingerMissCode

            fingers = xml.optJSONObject(NISTBiometricInformation).optJSONArray(PackageFingerprint);
            for (int i = 0; i < fingers.length(); i++) {
                System.out.println("NIST parser ---> parse: " + i);
                fingerObject.put("", "");
                fingers.put(fingerObject);
            }
            json.put("", fingers);
        }catch (Exception e){

            System.out.println("parse: " + e.getMessage());

        }
        return json;
    }
}
