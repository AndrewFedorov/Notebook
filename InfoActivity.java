package company.notebook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class InfoActivity extends ActionBarActivity {

    private int i = 0;
    SharedPreferences mContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        TextView textViewFio = (TextView)findViewById(R.id.textViewFio2);
        TextView textViewPhone = (TextView)findViewById(R.id.textViewPhone2);
        TextView textViewEmail = (TextView)findViewById(R.id.textViewEmail2);
        TextView textViewGroup = (TextView)findViewById(R.id.textViewGroup2);
        TextView textViewComment = (TextView)findViewById(R.id.textViewComment2);

        mContacts = getSharedPreferences("contacts", Context.MODE_PRIVATE);

        i = mContacts.getInt("position", 0);

        String fio = mContacts.getString("Surname" + String.valueOf(i), "") + " "
                + mContacts.getString("Name" + String.valueOf(i), "") + " "
                + mContacts.getString("Patronymic" + String.valueOf(i), "");

        textViewFio.setText(fio);
        textViewPhone.setText(mContacts.getString("phone" + String.valueOf(i), ""));
        textViewEmail.setText(mContacts.getString("email" + String.valueOf(i), ""));
        textViewGroup.setText(mContacts.getString("group" + String.valueOf(i), ""));
        textViewComment.setText(mContacts.getString("comment" + String.valueOf(i), ""));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_info, menu);
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

    public void onClickEdit(View view) {
        Intent intent = new Intent(InfoActivity.this, EditActivity.class);
        startActivity(intent);
    }

    public void onClickDelete(View view) {
        SharedPreferences.Editor edit = mContacts.edit();

        edit.remove("Surname" + String.valueOf(i));
        edit.remove("Name" + String.valueOf(i));
        edit.remove("Patronymic" + String.valueOf(i));
        edit.remove("phone" + String.valueOf(i));
        edit.remove("email" + String.valueOf(i));
        edit.remove("group" + String.valueOf(i));
        edit.remove("groupPosition" + String.valueOf(i));
        edit.remove("comment" + String.valueOf(i));
        edit.remove("date" + String.valueOf(i));

        int number = mContacts.getInt("number", 0);

        number--;

        for(int j = i; j < number; j++) {
            edit.putString("Surname" + String.valueOf(j), mContacts.getString("Surname" + String.valueOf(j + 1), ""));
            edit.putString("Name" + String.valueOf(j), mContacts.getString("Name" + String.valueOf(j + 1), ""));
            edit.putString("Patronymic" + String.valueOf(j), mContacts.getString("Patronymic" + String.valueOf(j + 1), ""));
            edit.putString("phone" + String.valueOf(j), mContacts.getString("phone" + String.valueOf(j + 1), ""));
            edit.putString("email" + String.valueOf(j), mContacts.getString("email" + String.valueOf(j + 1), ""));
            edit.putString("group" + String.valueOf(j), mContacts.getString("group" + String.valueOf(j + 1), ""));
            edit.putInt("groupPosition" + String.valueOf(j), mContacts.getInt("groupPosition" + String.valueOf(j + 1), 0));
            edit.putString("comment" + String.valueOf(j), mContacts.getString("comment" + String.valueOf(j + 1), ""));
            edit.putString("date" + String.valueOf(j), mContacts.getString("date" + String.valueOf(j + 1), ""));
            edit.commit();
        }

        edit.putInt("number", number);
        edit.commit();
        Intent intent = new Intent(InfoActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
