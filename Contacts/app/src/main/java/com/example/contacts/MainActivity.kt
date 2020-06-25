package com.example.contacts

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.db.dao.Contact
import com.example.contacts.db.dao.ContactDao
import com.example.contacts.settings.SettingsActivity

class MainActivity : AppCompatActivity() {
    private val dao = ContactDao()
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycleView)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.add -> {
                val intent = Intent(this, CreateContactActivity::class.java)
                startActivityForResult(intent, 1)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            dao.add(Contact(null, data.getStringExtra("name")!!, data.getStringExtra("number")!!))
        }
    }

    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val number: TextView = itemView.findViewById(R.id.number)
    }

    inner class ContactRecyclerViewAdapter(
        private var listItems: List<Contact>
    ) :
        RecyclerView.Adapter<ContactViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.contact, parent, false)
            return ContactViewHolder(view)
        }

        override fun getItemCount(): Int {
            return listItems.size
        }

        override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
            val listItem = listItems[position]
            holder.name.text = listItem.name
            holder.number.text = listItem.number
        }
    }

    override fun onResume() {
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)

        val listContacts: List<Contact> = dao.queryForAll()
        when (preferences.getString(getString(R.string.sort_option), "")) {
            "Name" -> recyclerView.adapter =
                ContactRecyclerViewAdapter(listContacts.sortedBy { it.name })
            "Number" -> recyclerView.adapter =
                ContactRecyclerViewAdapter(listContacts.sortedBy { it.number })
            else -> recyclerView.adapter = ContactRecyclerViewAdapter(listContacts)
        }

        super.onResume()
    }
}