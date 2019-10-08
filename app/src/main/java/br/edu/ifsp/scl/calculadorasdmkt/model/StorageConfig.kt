package br.edu.ifsp.scl.calculadorasdmkt.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StorageConfig(var database: Boolean = false): Parcelable