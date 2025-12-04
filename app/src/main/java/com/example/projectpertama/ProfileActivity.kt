package com.example.projectpertama

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    private lateinit var profileImage: ImageView
    private lateinit var nameText: TextView
    private lateinit var bioText: TextView
    private lateinit var birthDateText: TextView
    private lateinit var addressText: TextView
    private lateinit var jobText: TextView
    private lateinit var emailText: TextView
    private lateinit var phoneText: TextView
    private lateinit var instagramText: TextView
    private lateinit var whatsappText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layoutId = resources.getIdentifier("activity_profile", "layout", packageName)
        if (layoutId != 0) {
            setContentView(layoutId)
            initViews()
            setProfileData()
        }
    }

    private fun initViews() {
        try {
            profileImage = findViewById(getResId("profileImage", "id"))
            nameText = findViewById(getResId("nameText", "id"))
            bioText = findViewById(getResId("bioText", "id"))
            birthDateText = findViewById(getResId("birthDateText", "id"))
            addressText = findViewById(getResId("addressText", "id"))
            jobText = findViewById(getResId("jobText", "id"))
            emailText = findViewById(getResId("emailText", "id"))
            phoneText = findViewById(getResId("phoneText", "id"))
            instagramText = findViewById(getResId("instagramText", "id"))
            whatsappText = findViewById(getResId("whatsappText", "id"))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setProfileData() {
        nameText.text = "sᥲᥒძуᥲ gі\uD835\uDDBF\uD835\uDDCDᥲ ᥙᥣіmᥲz s.✌\uFE0E㋡"
        bioText.text = "\uD80C\uDD9F \uD80C\uDD9Eуᥲуᥲ's ᥣі\uD835\uDDCD\uD835\uDDCDᥣᥱ sіძᥱ\uD80C\uDD9D \uD80C\uDD9F"
        birthDateText.text = "Kamis, 12 Maret 2009"
        addressText.text = "Pabelan, Kartasura, Sukoharjo, Jawa Tengah"
        jobText.text = "SDIT Al-Kautsar, SMP Budi Utomo, Smk Negeri 6 SKA"
        emailText.text = "sandygifta@gmail.com"
        phoneText.text = "+62 851-5628-7843"
        instagramText.text = "sasandya.a"
        whatsappText.text = "sandyagifta-ui"

        val imageId = getResId("foto", "drawable")
        if (imageId != 0) {
            profileImage.setImageResource(imageId)
        }
    }

    private fun getResId(resourceName: String, resourceType: String): Int {
        return resources.getIdentifier(resourceName, resourceType, packageName)
    }

    fun updateProfile(
        name: String,
        bio: String,
        birthDate: String,
        address: String,
        education: String,
        email: String,
        phone: String,
        instagram: String,
        twitter: String
    ) {
        nameText.text = name
        bioText.text = bio
        birthDateText.text = birthDate
        addressText.text = address
        jobText.text = education
        emailText.text = email
        phoneText.text = phone
        instagramText.text = instagram
        whatsappText.text = twitter
    }
}