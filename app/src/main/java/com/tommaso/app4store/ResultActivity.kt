package com.tommaso.app4store

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import org.json.JSONArray
import org.json.JSONObject
import java.util.ArrayList

class ResultActivity : AppCompatActivity() {

    lateinit var pieChart: PieChart
    public val EMOTION_KEY: String = "EMOTION_KEY"
    lateinit var chipGroup:ChipGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val result: String = intent.extras?.get(EMOTION_KEY).toString()

        chipGroup=findViewById(R.id.chipGroup)

        var json: JSONObject = JSONObject(result)
        Toast.makeText(this, json.getString("emotions_detected"), Toast.LENGTH_LONG).show()

        var emotions_detected:JSONArray=json.getJSONArray("emotions_detected")

        for (i in 0..(emotions_detected.length()-1)) {
            val chip = Chip(chipGroup.context)
            chip.text= emotions_detected.getString(i)

            // necessary to get single selection working
            chip.isClickable = true
            chip.isCheckable = true
            chip.chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorAccent))
            chipGroup.addView(chip)
        }




        var emotionScores=json.getJSONObject("emotion_scores")

        var joy=emotionScores.getDouble("joy")
        var sadness=emotionScores.getDouble("sadness")
        var surprise=emotionScores.getDouble("surprise")
        var anger=emotionScores.getDouble("anger")
        var fear=emotionScores.getDouble("fear")
        var disgust=emotionScores.getDouble("disgust")


//        {"emotions_detected":["joy"],
//         "emotion_scores":{"joy":0.1345922573875,"sadness":0.022857406778851,"surprise":0.0087088390785547,"disgust":0,"anger":0,"fear":0},
//         "version":"7.0.0",
//         "author":"twinword inc.",
//         "email":"help@twinword.com",
//         "result_code":"200",
//         "result_msg":"Success"}


        pieChart = findViewById(R.id.pieChart)
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5F, 10F, 5F, 5F);

        pieChart.setDragDecelerationFrictionCoef(0.95f);


        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);

        pieChart.setTransparentCircleColor(Color.WHITE);
        pieChart.setTransparentCircleAlpha(110);

        pieChart.setHoleRadius(58f);
        pieChart.setTransparentCircleRadius(61f);

        pieChart.setDrawCenterText(true);

        pieChart.setRotationAngle(0F);
        // enable rotation of the chart by touch
        pieChart.setRotationEnabled(true);
        pieChart.setHighlightPerTapEnabled(true);

        // chart.setUnit(" €");
        // chart.setDrawUnitsInChart(true);
        var l: Legend = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        pieChart.setEntryLabelColor(Color.WHITE);
        pieChart.setEntryLabelTextSize(12f);

        val entries = ArrayList<PieEntry>()

        entries.add(PieEntry(joy.toFloat(), "joy"))
        entries.add(PieEntry(sadness.toFloat(), "sadness"))
        entries.add(PieEntry(surprise.toFloat(), "surprise"))
        entries.add(PieEntry(disgust.toFloat(), "disgust"))
        entries.add(PieEntry(anger.toFloat(), "anger"))
        entries.add(PieEntry(fear.toFloat(), "fear"))
        var dataSet: PieDataSet = PieDataSet(entries, "Emotions")
        val colors = ArrayList<Int>()
        for (c in ColorTemplate.VORDIPLOM_COLORS) colors.add(c)
        for (c in ColorTemplate.JOYFUL_COLORS) colors.add(c)
        for (c in ColorTemplate.COLORFUL_COLORS) colors.add(c)
        for (c in ColorTemplate.LIBERTY_COLORS) colors.add(c)
        for (c in ColorTemplate.PASTEL_COLORS) colors.add(c)
        for (c in ColorTemplate.MATERIAL_COLORS) colors.add(c)
        dataSet.colors = colors
        pieChart.data = PieData(dataSet)
    }


}
