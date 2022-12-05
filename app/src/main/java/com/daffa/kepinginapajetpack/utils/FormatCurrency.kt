package com.daffa.kepinginapajetpack.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

fun Double?.formatCurrencyRupiah(): String {
    val formatter = DecimalFormat("###,###,###.##")
    val formatRp = DecimalFormatSymbols()
    formatRp.groupingSeparator = '.'
    formatRp.decimalSeparator = ','
    formatter.decimalFormatSymbols = formatRp
    return "Rp. " + formatter.format(this)
}