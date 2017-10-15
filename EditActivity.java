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


public class EditActivity extends ActionBarActivity {

    private  int i = 0;
    SharedPreferences mContacts;
    EditText editSurname;
    EditText editName;
    EditText editPatronymic;
    EditText editPhone;
    EditText editEmail;
    Spinner spinnerGroup;
    EditText editComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        mContacts = getSharedPreferences("contacts", Context.MODE_PRIVATE);

        editSurname = (EditText)findViewById(R.id.editTextSurname);
        editName = (EditText)findViewById(R.id.editTextName);
        editPatronymic = (EditText)findViewById(R.id.editTextPatronymic);
        editPhone = (EditText)findViewById(R.id.editTextPhone);
        editEmail = (EditText)findViewById(R.id.editTextEmail);
        spinnerGroup = (Spinner)findViewById(R.id.spinnerGroup);
        editComment = (EditText)findViewById(R.id.editTextComment);

        i = mContacts.getInt("position", 0);

        editSurname.setText(mContacts.getString("Surname" + String.valueOf(i), ""));
        editName.setText(mContacts.getString("Name" + String.valueOf(i), ""));
        editPatronymic.setText(mContacts.getString("Patronymic" + String.valueOf(i), ""));
        editPhone.setText(mContacts.getString("phone" + String.valueOf(i), ""));
        editEmail.setText(mContacts.getString("email" + String.valueOf(i), ""));
        spinnerGroup.setSelection(mContacts.getInt("groupPosition" + String.valueOf(i), 0));
        editComment.setText(mContacts.getString("comment" + String.valueOf(i), ""));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit, menu);
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
        SharedPreferences.Editor edit = mContacts.edit();

        edit.putString("Surname" + String.valueOf(i), editSurname.getText().toString());
        edit.putString("Name" + String.valueOf(i), editName.getText().toString());
        edit.putString("Patronymic" + String.valueOf(i), editPatronymic.getText().toString());
        edit.putString("phone" + String.valueOf(i), editPhone.getText().toString());
        edit.putString("email" + String.valueOf(i), editEmail.getText().toString());
        edit.putString("group" + String.valueOf(i), spinnerGroup.getSelectedItem().toString());
        edit.putInt("groupPosition" + String.valueOf(i), spinnerGroup.getSelectedItemPosition());
        edit.putString("comment" + String.valueOf(i), editComment.getText().toString());
        edit.commit();

        Intent intent = new Intent(EditActivity.this, InfoActivity.class);
        startActivity(intent);
    }
}
