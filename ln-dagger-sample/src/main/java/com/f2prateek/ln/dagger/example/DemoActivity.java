/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2013 Prateek Srivastava
 * Copyright (c) 2010 Xtreme Labs and Pivotal Labs
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.f2prateek.ln.dagger.example;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.f2prateek.ln.Ln;

import static android.widget.Toast.LENGTH_SHORT;

public class DemoActivity extends Activity
    implements View.OnClickListener, AdapterView.OnItemSelectedListener {

  private static final String LOG_TEXT = "Button with id %d clicked with message '%s'";
  private EditText editText;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.demo_activity);

    findViewById(R.id.log).setOnClickListener(this);
    findViewById(R.id.thread).setOnClickListener(this);
    editText = (EditText) findViewById(R.id.text);

    Spinner spinner = (Spinner) findViewById(R.id.spinner);
    ArrayAdapter<CharSequence> adapter =
        ArrayAdapter.createFromResource(this, R.array.ln_level_array,
            android.R.layout.simple_spinner_item);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(adapter);
    spinner.setOnItemSelectedListener(this);
  }

  @Override public void onClick(View view) {
    Button button = (Button) view;
    if (button.getId() == R.id.thread) {
      new LogTask().execute(button);
    } else {
      logButtonClick(button);
    }

    Toast.makeText(this, "See logcat!", LENGTH_SHORT).show();
  }

  @Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    int level = position + 2; // log levels start at 2 but our adapter starts at 0
    Ln.d("Setting log level to %s", Ln.logLevelToString(level));
    Ln.setLoggingLevel(level);
  }

  @Override public void onNothingSelected(AdapterView<?> parent) {
    // Do Nothing
  }

  private void logButtonClick(Button button) {
    String message = editText.getText().toString();
    Ln.v(LOG_TEXT, button.getId(), message);
    Ln.d(LOG_TEXT, button.getId(), message);
    Ln.i(LOG_TEXT, button.getId(), message);
    Ln.w(LOG_TEXT, button.getId(), message);
    Ln.e(LOG_TEXT, button.getId(), message);
  }

  private class LogTask extends AsyncTask<Button, Void, Void> {

    @Override protected Void doInBackground(Button... buttons) {
      logButtonClick(buttons[0]);
      return null;
    }
  }
}