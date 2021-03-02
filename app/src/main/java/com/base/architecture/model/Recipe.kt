package com.base.architecture.model

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.ParcelField
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "recipes")
data class Recipe(

    @SerializedName("category")
    @Expose
    @ColumnInfo(name = "category")
    val category: String,


    @SerializedName("description")
    @Expose
    @ColumnInfo(name = "description")
    val description: String,

    @SerializedName("id")
    @Expose
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")val id: Int,

    @SerializedName("image")
    @Expose
    @ColumnInfo(name = "image")
    val image: String,

    @SerializedName("label")
    @Expose
    @ColumnInfo(name = "label")
    val label: String,

    @SerializedName("name")
    @Expose
    @ColumnInfo(name = "name")
    val name: String,

    @SerializedName("price")
    @Expose
    @ColumnInfo(name = "price")val price: String

) : Parcelable {
    override fun toString(): String {
        return "Recipe(category='$category', description='$description', id=$id, image='$image', label='$label', name='$name', price='$price')"
    }
}
