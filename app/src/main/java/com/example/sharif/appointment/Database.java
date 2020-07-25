package com.example.sharif.appointment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SHARIF on 8/13/2018.
 */

 public class Database extends SQLiteOpenHelper {
    long re;
    public static final String DBName = "Patient_Record";
    public static final int DBVersion = 1;
    public static final String TableName1 = "DR_Imran";
    public static final String TableName2 = "DR_Ali";
    public static final String TableName3 = "DR_Shaid";
    public static final String PatientName = "Patient_Name";
    public static final String PatientCnic = "Patient_CNIC";
    public static final String DAY = "Appointment_Day";
    public static final String P_place = "Patient_place";

    public Database(Context context) {
        super(context, DBName, null, DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // here we creating table and colum
        db.execSQL("create table " + TableName1 + "(" + PatientName + " TEXT," + PatientCnic + " TEXT PRIMARY KEY,"
                + DAY + " TEXT," + P_place + " TEXT);");
        db.execSQL("create table " + TableName2 + "(" + PatientName + " TEXT," + PatientCnic + " TEXT PRIMARY KEY,"
                + DAY + " TEXT," + P_place + " TEXT);");
        db.execSQL("create table " + TableName3 + "(" + PatientName + " TEXT," + PatientCnic + " TEXT PRIMARY KEY,"
                + DAY + " TEXT," + P_place + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableName1);
        db.execSQL("DROP TABLE IF EXISTS " + TableName2);
        db.execSQL("DROP TABLE IF EXISTS " + TableName3);
        onCreate(db);
    }

    public Boolean AddData(Patient record) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        if (TableName1.equals(record.getDr_name())) {
            cv.put(PatientName, record.getP_name());
            cv.put(PatientCnic, record.getCnic());
            cv.put(DAY, record.getDay());
            cv.put(P_place, record.getPlace());
            re = db.insert(TableName1, null, cv);
        } else if (TableName2.equals(record.getDr_name())) {
            cv.put(PatientName, record.getP_name());
            cv.put(PatientCnic, record.getCnic());
            cv.put(DAY, record.getDay());
            cv.put(P_place, record.getPlace());
            re = db.insert(TableName2, null, cv);
        } else {
            cv.put(PatientName, record.getP_name());
            cv.put(PatientCnic, record.getCnic());
            cv.put(DAY, record.getDay());
            cv.put(P_place, record.getPlace());
            re = db.insert(TableName2, null, cv);
        }
        db.close();
        if (re == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean upDate(Patient patient,String a){
        boolean b = false;
       int r= Delete(a);
       if(r>0) {
           b = AddData(patient);
       }
       if(b==true){
           return true;
       }
       else {
           return false;
       }
    }
    public int Delete(String CNIC) {

        SQLiteDatabase db = this.getWritableDatabase();
       int   a = db.delete(TableName1, "Patient_CNIC=?", new String[]{CNIC});
        if (a > 0) {
            return a;
        }
        else {
            a = db.delete(TableName2, "Patient_CNIC=?", new String[]{CNIC});
            if(a>0){
                return a;
            }
            else {
                a = db.delete(TableName3, "Patient_CNIC=?", new String[]{CNIC});
                return a;
            }
        }
    }
}