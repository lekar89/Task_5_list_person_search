package com.kalashnyk.denys.task_5_list_person_search;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.kalashnyk.denys.task_5_list_person_search.model.Person;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;




/**
 * Created by Влад on 23.12.2016.
 */

public class CRUDSharedPref {

    public final String PREF_NAME = "CRUD_APP";
    public final String PERSON_CONST = "PERSON";

    public void update(Context context, Person newPerson){
        ArrayList<Person> personList = getPersons(context);
        if (personList != null) {
            for (Person person: personList) {

                if(person.getId()==newPerson.getId()) person= newPerson;

            }

            savePersons(context, personList);
        }

    }



    public void savePersons(Context context, List<Person> person) {
        SharedPreferences preferences;
        SharedPreferences.Editor editor;

        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
        Gson gson = new Gson();
        String jsonPerson = gson.toJson(person);
        editor.putString(PERSON_CONST, jsonPerson);
        editor.commit();

    }

    public void addPerson(Context context, Person person) {
        List<Person> persons = getPersons(context);
        if (persons == null) persons = new ArrayList<Person>();
        persons.add(person);

        savePersons(context, persons);
    }

    public void removePerson(Context context, Person person) {
        ArrayList<Person> personList = getPersons(context);

        if (personList != null) {
            personList.remove(person);
            savePersons(context, personList);
            // Toast.makeText(context,"removePersonnnnnnnn",Toast.LENGTH_LONG).show();
        }
    }

    public ArrayList<Person> getPersons(Context context) {
        SharedPreferences preferences;
        List<Person> persons;
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        if (preferences.contains(PERSON_CONST)) {
            String jPer = preferences.getString(PERSON_CONST, null);
            Gson gson = new Gson();
            Person[] persons1 = gson.fromJson(jPer, Person[].class);

            persons = Arrays.asList(persons1);
            persons = new ArrayList<>(persons);
        } else
            return null;
        return (ArrayList<Person>) persons;
    }
    public  Person getPerson(Context mContext, String surname ) {


        AbstractList<Person> persons = getPersons(mContext);

        for (Person person : persons) {

            if (person.getSurname().equals(surname)) return person;
        }
        return null;
    }
}

