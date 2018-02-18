package com.panagiotiswarpro.panos.businessplan;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class BusinessCreationScreen extends AppCompatActivity {
    //public static String globalPreferences = "com."

    Button businessPlanButton,workersButton,proffesionalEquipmentButton,saveButton,proiontaButton;
    private File pdfFile;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 111;
    boolean synopsiHasText=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_creation_screen);

        businessPlanButton = (Button) findViewById(R.id.BusinessPlanButton);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        workersButton = (Button) findViewById(R.id.WorkersButton);
        proffesionalEquipmentButton = (Button) findViewById(R.id.ProfessionalEquipmentButton);
        saveButton = (Button) findViewById(R.id.saveButton);
        proiontaButton = (Button) findViewById(R.id.ProiontaButton);
        EditText synopsi = (EditText) findViewById(R.id.synopsh);

        //make the progressbar bigger
        progressBar.setScaleY(3f);

        businessPlanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BusinessCreationScreen.this,BusinessModel.class));
            }
        });

        proiontaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BusinessCreationScreen.this,ProiontaTab.class));
            }
        });


        workersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BusinessCreationScreen.this,Team.class));
            }
        });

        proffesionalEquipmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BusinessCreationScreen.this,Equipment.class));
            }
        });

        reloadPage();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText synopsi = (EditText) findViewById(R.id.synopsh);
                if(synopsi.getText().toString().isEmpty())
                {
                    Toast.makeText(BusinessCreationScreen.this,"Δεν γράψατε σύνοψη",Toast.LENGTH_LONG).show();
                }
                else{
                    try {
                        createPdfWrapper();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        synopsi.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
            int before, int count) {
                SharedPreferences sharedPreferences = getSharedPreferences("synopsi", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("synopsiText",s.toString());
                editor.apply();

                ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar2);

                if(s.length() != 0) {
                    if(!synopsiHasText) {
                        progressBar.setProgress(progressBar.getProgress() + 1);
                        synopsiHasText=true;
                    }
                }
                else {
                    progressBar.setProgress(progressBar.getProgress() - 1);
                    synopsiHasText=false;
                }
                saveButton.setEnabled(progressBar.getProgress()==5);
            }
        });
    }

    private void createPdfWrapper() throws FileNotFoundException,DocumentException{

        int hasWriteStoragePermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWriteStoragePermission != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_CONTACTS)) {
                    showMessageOKCancel("You need to allow access to Storage",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                                REQUEST_CODE_ASK_PERMISSIONS);
                                    }
                                }
                            });
                    return;
                }

                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE_ASK_PERMISSIONS);
            }
            return;
        }else {
            createPdf();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    try {
                        createPdfWrapper();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Permission Denied
                    Toast.makeText(this, "WRITE_EXTERNAL Permission Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    private void createPdf() throws FileNotFoundException, DocumentException {
        File docsFolder = new File(Environment.getExternalStorageDirectory() + "/Documents");
        if (!docsFolder.exists()) {
            docsFolder.mkdir();
        }

        pdfFile = new File(docsFolder.getAbsolutePath(),"Plan.pdf");
        OutputStream output = new FileOutputStream(pdfFile);
        Document document = new Document();
        PdfWriter.getInstance(document, output);
        document.open();

        try{
            InputStream in = getResources().openRawResource(R.raw.arialbd);
            FileOutputStream out = new FileOutputStream(Environment.getExternalStorageDirectory() + "/Documents/arialbd.ttf");
            byte[] buff = new byte[1024];
            int read = 0;

            try {
                while ((read = in.read(buff)) > 0) {
                    out.write(buff, 0, read);
                }
            } finally {
                in.close();
                out.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //itext needed a unicode font to support greek
        FontFactory.register(Environment.getExternalStorageDirectory() + "/Documents/arialbd.ttf","Greek-Regular");
        Font f = FontFactory.getFont("Greek-Regular", BaseFont.IDENTITY_H, true);

        EditText synopsi = (EditText) findViewById(R.id.synopsh);

        f.setStyle("bold");
        f.setSize(20);
        Paragraph centerSynopsi = new Paragraph("Σύνοψη\n\n",f);
        centerSynopsi.setAlignment(Element.ALIGN_CENTER);
        document.add(centerSynopsi);
        f.setSize(13);
        f.setStyle("normal");
        document.add(new Paragraph(synopsi.getText().toString()+"\n\n",f));

        f.setStyle("bold");
        f.setSize(20);
        Paragraph centerModel = new Paragraph("Επιχειρηματικό Μοντέλο\n\n",f);
        centerModel.setAlignment(Element.ALIGN_CENTER);
        document.add(centerModel);
        f.setSize(16);

        document.add(new Paragraph("Ταυτότητα\n\n",f));
        f.setSize(13);
        f.setStyle("normal");

        SharedPreferences sharedPref = getSharedPreferences("businessModel", Context.MODE_PRIVATE);
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Τόπος Εγκατάστασης: "+sharedPref.getString("ToposEgkastasis","")+
                "\nΗμερομηνία Έναρξης: " + sharedPref.getString("DateEgkatastash","")+
                "\nΜορφή Εταιρείας: ");

        switch(sharedPref.getInt("morfh", 0))
        {
            case 1:
                stringBuilder.append("Ατομική Επιχείρηση");
                break;
            case 2:
                stringBuilder.append("Συνεταιριστική Εταιρεία");
                break;
            case 3:
                stringBuilder.append("Ανώνυμη Εταιρεία");
                break;
        }

        stringBuilder.append("\n\nΤύπος Επιχείρησης:");
        if(sharedPref.getBoolean("paragwgikh", false))
            stringBuilder.append("\nΠαραγωγική");

        if(sharedPref.getBoolean("emporikh", false))
            stringBuilder.append("\nΕμπορική");

        if(sharedPref.getBoolean("paroxh", false))
            stringBuilder.append("\nΠαροχή υπηρεσιών");

        if(sharedPref.getBoolean("franchise", false))
            stringBuilder.append("\nFranchise");

        if(sharedPref.getBoolean("distrubutor", false))
            stringBuilder.append("\nDistributor");

        if(sharedPref.getBoolean("alloTypos", false)) {
            stringBuilder.append("\nΆλλο: ");
            stringBuilder.append(sharedPref.getString("PerigrafiAllo",""));
        }

        stringBuilder.append("\n\nΠελάτες Επιχείρησης:");
        if(sharedPref.getBoolean("idiotes", false))
            stringBuilder.append("\nΙδιώτες");

        if(sharedPref.getBoolean("epixeirhseis", false))
            stringBuilder.append("\nΕπιχειρήσεις");

        if(sharedPref.getBoolean("alloPelates", false)) {
            stringBuilder.append("\nΆλλο: ");
            stringBuilder.append(sharedPref.getString("PerigrafiAlloPelates",""));
        }
        String BusinessModel = stringBuilder.toString();
        document.add(new Paragraph(BusinessModel,f));

        f.setSize(16);
        f.setStyle("bold");
        document.add(new Paragraph("\nΠεριγραφή\n\n",f));
        f.setSize(13);
        f.setStyle("normal");
        document.add(new Paragraph( sharedPref.getString("perigrafh", ""),f));

        document.add(new Paragraph("\n\n"));

        SharedPreferences sharedTeamTabs = getSharedPreferences("TeamTabs", Context.MODE_PRIVATE);
        f.setStyle("bold");
        f.setSize(20);
        Paragraph centerModel2 = new Paragraph("Ομάδα υλοποίησης και δοίκησης\n\n",f);
        centerModel2.setAlignment(Element.ALIGN_CENTER);
        document.add(centerModel2);
        f.setSize(16);

        document.add(new Paragraph("Διοικητές:\n",f));

        f.setStyle("normal");
        f.setSize(13);

        document.add(new Paragraph(sharedTeamTabs.getString("Dioikhtes","") + "\n",f));

        f.setStyle("bold");
        f.setSize(16);

        document.add(new Paragraph("Εργατικό Δυναμικό:\n",f));


        f.setStyle("normal");
        f.setSize(13);

        document.add(new Paragraph(sharedTeamTabs.getString("Ergates","") + "\n",f));

        f.setStyle("bold");
        f.setSize(15);

        document.add(new Paragraph("Συνολικό Κόστος Εργατικού Δυναμικού: " + sharedTeamTabs.getInt("Kostos",0) + "\n\n",f));

        SharedPreferences shareEquipmentTabs = getSharedPreferences("equipmentTabs", Context.MODE_PRIVATE);

        f.setStyle("bold");
        f.setSize(20);
        Paragraph centerModel3 = new Paragraph("Εγκαταστάσεις και Εξοπλισμός:\n",f);
        centerModel3.setAlignment(Element.ALIGN_CENTER);
        document.add(centerModel3);

        f.setStyle("normal");
        f.setSize(13);

        document.add(new Paragraph(shareEquipmentTabs.getString("equipmentLista",""),f));

        f.setStyle("bold");
        f.setSize(15);

        document.add(new Paragraph("\nΣυνολικό Κόστος Εγκαταστάσεων και Εξοπλισμού: " + shareEquipmentTabs.getInt("sunolikaEksoda",0) + "\n\n\n",f));

        SharedPreferences sharedProiontaTabs = getSharedPreferences("proiontaTabs", Context.MODE_PRIVATE);

        f.setStyle("bold");
        f.setSize(20);
        Paragraph centerModel4 = new Paragraph("Προϊόντα ή Υπηρεσίες:\n",f);
        centerModel4.setAlignment(Element.ALIGN_CENTER);
        document.add(centerModel4);

        f.setStyle("normal");
        f.setSize(13);

        document.add(new Paragraph(sharedProiontaTabs.getString("esodaProiontaLista","") + "\n",f));

        f.setStyle("bold");
        f.setSize(15);

        document.add(new Paragraph("Συνολικά Έσοδα Προϊόντων ή Υπηρεσιών: " + sharedProiontaTabs.getInt("sunolikaEsoda",0) + "\n",f));

        document.close();

        previewPdf();
    }

    private void previewPdf() {
        Intent target = new Intent(Intent.ACTION_VIEW);
        target.setDataAndType(Uri.fromFile(pdfFile),"application/pdf");
        target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

        Intent intent = Intent.createChooser(target, "Open File");
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this,"Download a PDF Viewer to see the generated PDF",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        reloadPage();
    }

    public void reloadPage()
    {
        EditText synopsi = (EditText) findViewById(R.id.synopsh);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        progressBar.setProgress(0);

        //removeFocus from editTexts
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //βλέπω αν τα στοιχεία του businessModel αποθηκεύτηκαν για να συνεχίσει η πρόοδος..
        SharedPreferences sharedBusinessModel = getSharedPreferences("businessModel", Context.MODE_PRIVATE);
        if(sharedBusinessModel.getBoolean("businessModelDone", false))
        {
            businessPlanButton.setBackgroundColor(Color.GREEN);
            progressBar.setProgress(progressBar.getProgress()+1);
        }

        //βλέπω αν τα στοιχεία του proiontaTabs αποθηκεύτηκαν για να συνεχίσει η πρόοδος..
        SharedPreferences sharedProionta = getSharedPreferences("proiontaTabs",Context.MODE_PRIVATE);
        if(sharedProionta.getBoolean("ProiontaTabsDone",false)){

            proiontaButton.setBackgroundColor(Color.GREEN);
            progressBar.setProgress((progressBar.getProgress()+1));
        }

        //βλέπω αν τα στοιχεία του TeamTabs αποθηκεύτηκαν για να συνεχίσει η πρόοδος..
        SharedPreferences sharedTeamTabs = getSharedPreferences("TeamTabs", Context.MODE_PRIVATE);
        if(sharedTeamTabs.getBoolean("TeamTabsDone",false)){

            workersButton.setBackgroundColor(Color.GREEN);
            progressBar.setProgress(progressBar.getProgress()+1);

        }

        //βλέπω αν τα στοιχεία του EquipmentTabs αποθηκεύτηκαν για να συνεχίσει η πρόοδος..
        SharedPreferences sharedEquipmentTabs = getSharedPreferences("equipmentTabs", Context.MODE_PRIVATE);
        if(sharedEquipmentTabs.getBoolean("EquipmentTabsDone",false)){

            proffesionalEquipmentButton.setBackgroundColor(Color.GREEN);
            progressBar.setProgress(progressBar.getProgress()+1);

        }

        SharedPreferences sharedSynopsi = getSharedPreferences("synopsi", Context.MODE_PRIVATE);
        if(!sharedSynopsi.getString("synopsiText","").isEmpty()) {
            synopsi.setText(sharedSynopsi.getString("synopsiText", ""));
            progressBar.setProgress(progressBar.getProgress() + 1);
            synopsiHasText=true;
        }

        saveButton.setEnabled(progressBar.getProgress()==5);
    }
}
