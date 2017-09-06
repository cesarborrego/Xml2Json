package com.company;

import org.json.JSONArray;
import org.json.JSONObject;

public class NistXmlParser {
    //KeyService
    public static final String KS_RH_SEMILLAS = "0201";
    public static final String KS_RH_MASIVO = "0202";
    public static final String KS_RH_RECURRENTE = "0203";
    public static final String KS_RH_REGISTRO = "0301";

    //KeyModule
    public static final String MODULE_RM = "RM";
    public static final String MODULE_RR = "RR";
    public static final String MODULE_RT = "RT";
    public static final String MODULE_MD = "MD";
    public static final String MODULE_RP = "RP";
    public static final String MODULE_PM = "PM";

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
    public static final String PackageFingerprint = "itl:PackageFingerprintImageRecord";
    public static final String ImageReferenceIdentification = "biom:ImageReferenceIdentification";
    public static final String FingerImpressionImage = "biom:FingerImpressionImage";


    public static final String identificationID = "biom:FingerPositionCode";
    public static final String imageBitsPerPixelQuantity = "biom:ImageBitsPerPixelQuantity";

    //Este valor sólo aparece cuando existe una huella registrada
    public static final String imageHashValue = "biom:ImageHashValue";
    public static final String imageCaptureDetail = "biom:ImageCaptureDetail";
    public static final String captureDate = "biom:CaptureDate";
    public static final String dateTime = "nc:DateTime";
    public static final String captureDeviceIdentification = "biom:CaptureDeviceIdentification";
    public static final String identificationIDCaptureDeviceIdentification = "nc:IdentificationID";
    public static final String captureResolutionCode = "biom:CaptureResolutionCode";
    public static final String imageCompressionAlgorithmCode = "biom:ImageCompressionAlgorithmCode";
    public static final String imageScaleUnitsCode = "biom:ImageScaleUnitsCode";
    public static final String FingerprintImageNISTQuality = "biom:FingerprintImageNISTQuality";
    public static final String nistQualityMeasure = "biom:NISTQualityMeasure";
    public static final String fingerprintImageAcquisitioneProfileCode = "biom:FingerprintImageAcquisitionProfileCode";
    public static final String FingerprintImageFingerMissing = "biom:FingerprintImageFingerMissing";
    public static final String fingerMissingCode = "biom:FingerMissingCode";
    public static final String DatosAdicionalesHuella = "itl:DatosAdicionalesHuella";
    public static final String tipoPersona = "prc:tipoPersona";

    public static final String PackageDescriptiveTextRecord = "itl:PackageDescriptiveTextRecord";
    public static final String DatosEnrolTrabajador = "itl:DatosEnrolTrabajador";
    public static final String curpTrabajador = "prc:curpTrabajador";
    public static final String curpEmpleado = "prc:curpEmpleado";
    public static final String fechaFirma = "prc:fechaFirma";
    public static final String recordCategoryCode = "biom:RecordCategoryCode";
    private static final String ENROLL_AFORE = "564";


    public static JSONObject parse(JSONObject xml) {

        JSONObject json = new JSONObject();
        JSONArray fingers = new JSONArray();
        StringBuilder fingerprintStr = new StringBuilder();
        String tipoPersonaEnrolledStr = "";
        String tipoPersonaEnrollerStr = "";
        StringBuilder imageBitsPPQuantityStr = new StringBuilder();
        StringBuilder captureDateStr = new StringBuilder();
        StringBuilder idetificationIdStr = new StringBuilder();
        StringBuilder captureResolutionCodeStr = new StringBuilder();
        StringBuilder imageCompressionAlgorithmCodeStr = new StringBuilder();
        StringBuilder imageScaleUnitsCodeStr = new StringBuilder();
        StringBuilder nistQualityMeasureStr = new StringBuilder();
        StringBuilder fingerPrintImageAcquisitionProfileCodeStr = new StringBuilder();
        StringBuilder fingerMissingCodeStr = new StringBuilder();

        try {
            //Clave Servicio
            json.put(KEY_SERVICE, KS_RH_REGISTRO);
            //Modulo
            json.put(MODULE, MODULE_RT);
            //curp_enrolado
            json.put(ENRROLLED_CURP, xml.optJSONObject(NISTBiometricInformation).optJSONObject(PackageDescriptiveTextRecord).optJSONObject(DatosEnrolTrabajador).optString(curpTrabajador));
            //curp_enrolador
            json.put(ENRROLLER_CURP, xml.optJSONObject(NISTBiometricInformation).optJSONObject(PackageDescriptiveTextRecord).optJSONObject(DatosEnrolTrabajador).optString(curpEmpleado));
            //fecha_firma
            json.put(SIGNATURE_DATE, xml.optJSONObject(NISTBiometricInformation).optJSONObject(PackageDescriptiveTextRecord).optJSONObject(DatosEnrolTrabajador).optString(fechaFirma));
            //DeliveryDate
            json.put(DELIVERY_DATE, "2017-07-20T10:11:00.528-05:00");
            //KeyEnrollAfore
            json.put(KEY_ENROLL_AFORE, ENROLL_AFORE);

            fingers = xml.optJSONObject(NISTBiometricInformation).optJSONArray(PackageFingerprint);
            for (int i = 0; i < fingers.length(); i++) {
                System.out.println("NIST parser" + i);

                JSONObject fingerObject = fingers.getJSONObject(i);
                //TypePersonEnrolled
                JSONObject datosAdicionalesHuellaObj = fingerObject.getJSONObject(DatosAdicionalesHuella);
                if (i < fingers.length() - 1) {
                    tipoPersonaEnrolledStr = String.valueOf(datosAdicionalesHuellaObj.getInt(tipoPersona));
                } else {

                    //TypePersonEnroller
                    tipoPersonaEnrollerStr = String.valueOf(datosAdicionalesHuellaObj.getInt(tipoPersona));
                }

                //IMAGE_BITS_PP_QUANTITY
                JSONObject imagePPQuantityObj = fingerObject.getJSONObject(FingerImpressionImage);
                imageBitsPPQuantityStr.append(String.valueOf(imagePPQuantityObj.getInt(imageBitsPerPixelQuantity)));
                if (i < fingers.length() - 1) {
                    imageBitsPPQuantityStr.append("|");
                }

                //Si existe hash value, quiere decir que si hay huella, por lo que si existe el valor de CaptureDate
                if (fingerObject.toString().contains(imageHashValue)) {
                    System.out.println("NIST parser Si hay huella " + i);
                    //CAPTURE_DATE
                    JSONObject imageCaptureDetailObj = imagePPQuantityObj.getJSONObject(imageCaptureDetail);
                    JSONObject captureDateObj = imageCaptureDetailObj.getJSONObject(captureDate);
                    captureDateStr.append(captureDateObj.getString(dateTime));
                    if (i < fingers.length() - 1) {
                        captureDateStr.append("|");
                    }

                    //IDENTIFICATION_ID
                    JSONObject captureDeviceIdentificationObj = imageCaptureDetailObj.getJSONObject(captureDeviceIdentification);
                    idetificationIdStr.append(captureDeviceIdentificationObj.get(identificationIDCaptureDeviceIdentification));
                    if (i < fingers.length() - 1) {
                        idetificationIdStr.append("|");
                    }

                    //CAPTURE_RESOLUTION_CODE
                    captureResolutionCodeStr.append(imageCaptureDetailObj.getInt(captureResolutionCode));
                    if (i < fingers.length() - 1) {
                        captureResolutionCodeStr.append("|");
                    }

                    //IMAGE_COMPRESSION_ALGORITHM_CODE
                    imageCompressionAlgorithmCodeStr.append(imagePPQuantityObj.getInt(imageCompressionAlgorithmCode));
                    if (i < fingers.length() - 1) {
                        imageCompressionAlgorithmCodeStr.append("|");
                    }

                    //IMAGE_SCALE_UNITS_CODE
                    imageScaleUnitsCodeStr.append(imagePPQuantityObj.getInt(imageScaleUnitsCode));
                    if (i < fingers.length() - 1) {
                        imageScaleUnitsCodeStr.append("|");
                    }

                    //FINGERPRINT_IMAGE_NIST_QUALITY----De esta posicion del json se obtiene la calidad y la posicion del dedo capturado
                    JSONObject fingerPrintImageNistQualityObj = imagePPQuantityObj.getJSONObject(FingerprintImageNISTQuality);

                    //FINGERPRINT
                    //JSONObject identificationIdObj = fingerPrintImageNistQualityObj.getJSONObject(ImageReferenceIdentification);
                    //Se concatena 0 a los dígitos menores a 10 para enviar el paquete de dos dígitos como lo pide el requerimiento
                    if (fingerPrintImageNistQualityObj.getInt(identificationID) < 10) {
                        fingerprintStr.append("0");
                    }
                    fingerprintStr.append(String.valueOf(fingerPrintImageNistQualityObj.getInt(identificationID)));
                    if (i < fingers.length() - 1) {
                        fingerprintStr.append("|");
                    }
                    //FINGERPRINT_IMAGE_NIST_QUALITY
                    nistQualityMeasureStr.append(fingerPrintImageNistQualityObj.getInt(nistQualityMeasure));
                    if (i < fingers.length() - 1) {
                        nistQualityMeasureStr.append("|");
                    }

                    //FINGERPRINT IMAGE ACQUISITION PROFILE CODE
                    //Todas las huellas menos la última cuenta con este valor
                    if (i < fingers.length() - 1) {
                        fingerPrintImageAcquisitionProfileCodeStr.append(imagePPQuantityObj.getInt(fingerprintImageAcquisitioneProfileCode));
                        /*
                        if (i < fingers.length() - 2) {
                            fingerPrintImageAcquisitionProfileCodeStr.append("|");
                        }*/
                        //Se comenta el codigo de arriba porque anteriormente se mandaban solo 10 valores que son con los que cuenta el nist
                        //Ahora piden que si no existe se mande un vacio
                        fingerPrintImageAcquisitionProfileCodeStr.append("|");
                    }

                    //FingerMissCode debe de contener 10 valores. Las huellas son en total 11 pero la ultima es del agente por la que no cuenta
                    //Siempre tiene que haber dedo y por eso no existe una excepcion para el onceavo dedo
                    fingerMissingCodeStr.append("");
                    if (i < fingers.length() - 2) {
                        fingerMissingCodeStr.append("|");
                    }

                } else {
                    //No hay huella, entonces se manda vacío
                    captureDateStr.append("");
                    if (i < fingers.length() - 1) {
                        captureDateStr.append("|");
                    }

                    idetificationIdStr.append("");
                    if (i < fingers.length() - 1) {
                        idetificationIdStr.append("|");
                    }

                    captureResolutionCodeStr.append("");
                    if (i < fingers.length() - 1) {
                        captureResolutionCodeStr.append("|");
                    }

                    imageCompressionAlgorithmCodeStr.append("");
                    if (i < fingers.length() - 1) {
                        imageCompressionAlgorithmCodeStr.append("|");
                    }

                    imageScaleUnitsCodeStr.append("");
                    if (i < fingers.length() - 1) {
                        imageScaleUnitsCodeStr.append("|");
                    }

                    fingerprintStr.append("");
                    if (i < fingers.length() - 1) {
                        fingerprintStr.append("|");
                    }

                    nistQualityMeasureStr.append("");
                    if (i < fingers.length() - 1) {
                        nistQualityMeasureStr.append("|");
                    }

                    fingerPrintImageAcquisitionProfileCodeStr.append("");
                    if (i < fingers.length() - 1) {
                        fingerPrintImageAcquisitionProfileCodeStr.append("|");
                    }

                    JSONObject fingerprintImageFingerMissingObj = imagePPQuantityObj.getJSONObject(FingerprintImageFingerMissing);
                    fingerMissingCodeStr.append(fingerprintImageFingerMissingObj.getString(fingerMissingCode));
                    if (i < fingers.length() - 2) {
                        fingerMissingCodeStr.append("|");
                    }
                }
            }
            json.put(PERSON_ENROLLED_TYPE, tipoPersonaEnrolledStr);
            json.put(PERSON_ENROLLER_TYPE, tipoPersonaEnrollerStr);
            json.put(FINGERPRINT, fingerprintStr.toString());
            json.put(IMAGE_BITS_PP_QUANTITY, imageBitsPPQuantityStr.toString());
            json.put(CAPTURE_DATE, captureDateStr);
            json.put(IDENTIFICATION_ID, idetificationIdStr);
            json.put(CAPTURE_RESOLUTION, captureResolutionCodeStr);
            json.put(IMAGE_COMPRESSION_ALGORITHM, imageCompressionAlgorithmCodeStr);
            json.put(IMAGE_SCALE_UNITS_CODE, imageScaleUnitsCodeStr);
            json.put(NIST_QUALITY_MEASURE, nistQualityMeasureStr);
            json.put(FINGERPRINT_IMAGE_ACQUISITION, fingerPrintImageAcquisitionProfileCodeStr);
            json.put(FINGER_MISS_CODE, fingerMissingCodeStr);

        } catch (Exception e) {

            System.out.println("parse: " + e.getMessage());

        }
        return json;
    }
}
