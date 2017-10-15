package company.notebook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AddActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickSave(View view) {
        SharedPreferences mContacts = getSharedPreferences("contacts", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = mContacts.edit();
        int number = 0;

        EditText editSurname = (EditText)findViewById(R.id.editTextSurname);
        EditText editName = (EditText)findViewById(R.id.editTextName);
        EditText editPatronymic = (EditText)findViewById(R.id.editTextPatronymic);
        EditText editPhone = (EditText)findViewById(R.id.editTextPhone);
        EditText editEmail = (EditText)findViewById(R.id.editTextEmail);
        Spinner spinnerGroup = (Spinner)findViewById(R.id.spinnerGroup);
        EditText editComment = (EditText)findViewById(R.id.editTextComment);


        number = mContacts.getInt("number", 0);

        edit.putString("Surname" + String.valueOf(number), editSurname.getText().toString());
        edit.putString("Name" + String.valueOf(number), editName.getText().toString());
        edit.putString("Patronymic" + String.valueOf(number), editPatronymic.getText().toString());
        edit.putString("phone" + String.valueOf(number), editPhone.getText().toString());
        edit.putString("email" + String.valueOf(number), editEmail.getText().toString());
        edit.putString("group" + String.valueOf(number), spinnerGroup.getSelectedItem().toString());
        edit.putInt("groupPosition" + String.valueOf(number), spinnerGroup.getSelectedItemPosition());
        edit.putString("comment" + String.valueOf(number), editComment.getText().toString());
        edit.putString("comment" + String.valueOf(number), editComment.getText().toString());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy, HH:mm");
        edit.putString("date" + String.valueOf(number), dateFormat.format(new Date()));
        number++;
        edit.putInt("number", number);
        edit.commit();

        Intent intent = new Intent(AddActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
