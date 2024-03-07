package com.thinhnb.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.UUID;

public class MainActivity2 extends AppCompatActivity {
 TextView tvKQ;
 FirebaseFirestore database;
 Context context = this;
 Todo todo = null;

 @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvKQ = findViewById(R.id.tvKQ);
        database = FirebaseFirestore.getInstance();
//insert();
//update();
//select();
delete();
    }
    void insert(){
        String id = UUID.randomUUID().toString();
        todo = new Todo(id,"title 11","content 11");
        database.collection("TODO")
                .document(id)
                .set(todo.convertToHashMap())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(context,"insert thanh cong ", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
Toast.makeText(context,"insert that bai", Toast.LENGTH_SHORT).show();
                    }
                });
    }

void update()
{
    String id = "a2601db3-e437-4539-b21b-524cf3662720";
    todo = new Todo(id, "title 11 update ", "content 11 update");
    database.collection("TODO").document(id)
            .update(todo.convertToHashMap())
            .addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(context,"Update thanh cong ", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(context,"Update that bai ", Toast.LENGTH_SHORT).show();
                }
            });
}
void delete(){
     String id= "";
     database.collection("TODO")
             .document()
             .delete()
             .addOnSuccessListener(new OnSuccessListener<Void>() {
                 @Override
                 public void onSuccess(Void unused) {
                    Toast.makeText(context, "Delete Thanh Cong",Toast.LENGTH_SHORT).show();
                 }
             }).addOnFailureListener(new OnFailureListener() {
                 @Override
                 public void onFailure(@NonNull Exception e) {
                     Toast.makeText(context, "Delete That Bai",Toast.LENGTH_SHORT).show();
                 }
             });
}
    String strKQ  ="";
    ArrayList<Todo> select()
    {
        ArrayList<Todo> list=new ArrayList<>();
        database.collection("TODO")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful())
                        {
                            strKQ="";
                            for(QueryDocumentSnapshot doc: task.getResult())
                            {
                                Todo t=doc.toObject(Todo.class);//chuyen ket qua sang object
                                strKQ+="id: "+t.getId()+"\n";
                                strKQ+="title: "+t.getTitle()+"\n";
                                strKQ+="content: "+t.getContent()+"\n";
                                list.add(t);
                            }
                            Toast.makeText(context, "Doc thanh cong", Toast.LENGTH_SHORT).show();
                            tvKQ.setText(strKQ);
                        }
                        else {
                            Toast.makeText(context, "Doc khong thanh cong", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
        return list;
    }

}