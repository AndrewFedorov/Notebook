package company.notebook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView)findViewById(R.id.listViewContacts);
        final SharedPreferences mContacts = getSharedPreferences("contacts", Context.MODE_PRIVATE);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        int number = 0;
        String fio = " ";

        number = mContacts.getInt("number", 0);

        for(int i = 0; i < number; i++) {
            fio = mContacts.getString("Surname" + String.valueOf(i), "") + " "
                    + mContacts.getString("Name" + String.valueOf(i), "") + " "
                    + mContacts.getString("Patronymic" + String.valueOf(i), "");
            adapter.add(fio + "\n" + mContacts.getString("date" + String.valueOf(i), ""));
        }

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences.Editor edit = mContacts.edit();
                edit.putInt("position", position);
                edit.commit();
                Intent intentInfo = new Intent(MainActivity.this, InfoActivity.class);
                startActivity(intentInfo);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void onClickAdd(View view) {
        Intent intentAdd = new Intent(MainActivity.this, AddActivity.class);
        startActivity(intentAdd);
    }
}