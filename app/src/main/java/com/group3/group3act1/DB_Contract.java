package com.group3.group3act1;

import android.provider.BaseColumns;

public class DB_Contract {

    public static class tb_Account implements BaseColumns{

        //table name
        public static String ACCOUNT_TABLE = "table_account";
        //columns
        public static String ACCOUNT_ID = "account_id";
        public static String ACCOUNT_NAME = "account_name";
        public static String ACCOUNT_USERNAME = "account_username";
        public static String ACCOUNT_PASSWORD = "account_password";
        public static String ACCOUNT_BIRTHDATE = "acocunt_bday";
        public static String ACCOUNT_GENDER = "acocunt_gender";
        public static String ACCOUNT_HOBBIES = "account_hobbies";
        public static String ACCOUNT_ADDRESS = "account_address";
        public static String ACCOUNT_CONTACT = "account_contact";
        public static String ACCOUNT_SECURITY_1 = "account_sec1";
        public static String ACCOUNT_SECURITY_2 = "account_sec2";
        public static String ACCOUNT_SECURITY_3 = "account_sec3";
        public static String ACCOUNT_IMAGE_PATH = "account_image";



    }

    public static class tb_Entry implements BaseColumns{



        //table name
        public static String ENTRY_TABLE = "table_entry";
        //columns
        public static String ENTRY_ID = "entry_id";
        public static String ACCOUNT_ID = "account_id"; //foreign key
        public static String ENTRY_NAME = "entry_name";
        public static String ENTRY_REMARK = "entry_remark";
        public static String ENTRY_GENDER = "entry_gender";
        public static String ENTRY_CONTACT = "entry_contact";
        public static String ENTRY_HOBBIES = "entry_hobbies";
        public static String ENTRY_OTHERINFORMATION= "entry_other";
        public static String ENTRY_BIRTHDAY = "entry_bday";
        public static String ENTRY_IMAGE = "entry_image";
        public static String ENTRY_ADDRESS = "entry_address";





      /*  birthdate;
        private String entryGender;
        private String entryAddress;
        private String entryContact;
        private String entryHobbies;
        private String otherInformation;
*/

    }



}
