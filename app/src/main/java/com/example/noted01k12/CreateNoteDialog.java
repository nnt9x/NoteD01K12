package com.example.noted01k12;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

public abstract class CreateNoteDialog extends Dialog {

    public CreateNoteDialog(@NonNull Context context) {
        super(context);
    }

    private EditText edtTitle;
    private EditText edtContent;
    private Button btnYes, btnNo;

    protected abstract void sendToMain(Note note);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_note_item);
        setCancelable(false);
        edtTitle = findViewById(R.id.edtNoteTitle);
        edtContent = findViewById(R.id.edtNoteContent);

        btnYes = findViewById(R.id.btnYes);
        btnNo = findViewById(R.id.btnNo);

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Laay du lieu va gui ve MainActivity
                String title = edtTitle.getText().toString();
                if(title.isEmpty()){
                    edtTitle.setError("Khong de trong");
                    return;
                }
                String content = edtContent.getText().toString();
                if(content.isEmpty()){
                    edtContent.setError("Khong de trong");
                    return;
                }

                Note note = new Note(title, content);
                sendToMain(note);
                dismiss();
                edtTitle.setText("");
                edtContent.setText("");
            }
        });

    }
}
