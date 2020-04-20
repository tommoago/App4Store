package com.tommaso.app4store
//
//import android.graphics.Color
//import com.github.mikephil.charting.data.PieData
//import com.github.mikephil.charting.data.PieDataSet
//import com.github.mikephil.charting.data.PieEntry
//import com.github.mikephil.charting.formatter.PercentFormatter
//import com.github.mikephil.charting.utils.ColorTemplate
//import com.github.mikephil.charting.utils.MPPointF
//import java.util.*
//
//class Asda {
//    private fun setData(count: Int, range: Float) {
//        val entries = ArrayList<PieEntry>()
//
//        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
//        // the chart.
//        for (i in 0 until count) {
//            entries.add(
//                PieEntry(
//                    (Math.random() * range + range / 5).toFloat(),
//                    parties.get(i % parties.length),
//                    getResources().getDrawable(R.drawable.star)
//                )
//            )
//        }
//        val dataSet = PieDataSet(entries, "Election Results")
//        dataSet.setDrawIcons(false)
//        dataSet.sliceSpace = 3f
//        dataSet.iconsOffset = MPPointF(0, 40)
//        dataSet.selectionShift = 5f
//
//        // add a lot of colors
//        val colors = ArrayList<Int>()
//        for (c in ColorTemplate.VORDIPLOM_COLORS) colors.add(c)
//        for (c in ColorTemplate.JOYFUL_COLORS) colors.add(c)
//        for (c in ColorTemplate.COLORFUL_COLORS) colors.add(c)
//        for (c in ColorTemplate.LIBERTY_COLORS) colors.add(c)
//        for (c in ColorTemplate.PASTEL_COLORS) colors.add(c)
//        colors.add(ColorTemplate.getHoloBlue())
//        dataSet.colors = colors
//        //dataSet.setSelectionShift(0f);
//        val data = PieData(dataSet)
//        data.setValueFormatter(PercentFormatter(chart))
//        data.setValueTextSize(11f)
//        data.setValueTextColor(Color.WHITE)
//        // data.setValueTypeface(tfLight);
////        chart.setData(data);
////
////        // undo all highlights
////        chart.highlightValues(null);
////
////        chart.invalidate();
//    }
//}