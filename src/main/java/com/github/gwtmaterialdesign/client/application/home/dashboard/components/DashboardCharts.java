package com.github.gwtmaterialdesign.client.application.home.dashboard.components;

/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2016 GwtMaterialDesign
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.RadarChart;
import gwt.material.design.amcharts.client.XYChart;
import gwt.material.design.amcharts.client.axis.AxisLabel;
import gwt.material.design.amcharts.client.axis.CategoryAxis;
import gwt.material.design.amcharts.client.axis.DateAxis;
import gwt.material.design.amcharts.client.axis.ValueAxis;
import gwt.material.design.amcharts.client.column.Column;
import gwt.material.design.amcharts.client.cursor.RadarCursor;
import gwt.material.design.amcharts.client.cursor.XYCursor;
import gwt.material.design.amcharts.client.series.LineSeries;
import gwt.material.design.amcharts.client.series.RadarColumnSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.adapter.Adapter;
import gwt.material.design.amcore.client.base.InterfaceColorSet;
import gwt.material.design.amcore.client.base.Percent;
import gwt.material.design.amcore.client.color.Color;
import gwt.material.design.amcore.client.scrollbar.Scrollbar;
import gwt.material.design.client.ui.MaterialRow;

public class DashboardCharts extends Composite {

    private static DashboardCardUiBinder uiBinder = GWT.create(DashboardCardUiBinder.class);


    interface DashboardCardUiBinder extends UiBinder<Widget, DashboardCharts> {
    }

    @UiField
    MaterialRow activeUsersRow, performanceRowChart;

    public DashboardCharts() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    protected void onLoad() {
        super.onLoad();

        generateActiveUsers();
        generatePerformance();
    }

    protected void generatePerformance() {
        XYChart chart = (XYChart) Am4Core.create(performanceRowChart, Am4Charts.XYChart);

        chart.dataSource.url = "data/large-data.json";

        // Create axes
        DateAxis dateAxis = (DateAxis) chart.xAxes.push(new DateAxis());
        dateAxis.renderer.minGridDistance = 60;

        ValueAxis valueAxis = (ValueAxis) chart.yAxes.push(new ValueAxis());

        // Create series
        LineSeries series = (LineSeries) chart.series.push(new LineSeries());
        series.dataFields.valueY = "value";
        series.dataFields.dateX = "day";
        series.tooltipText = "{value}";
        series.stroke = new Color("#6771dc");
        series.strokeWidth = 2;

        series.tooltip.pointerOrientation = "vertical";

        chart.cursor = new XYCursor();

        chart.scrollbarY = new Scrollbar();
        chart.scrollbarX = new Scrollbar();

        chart.events.on("ready", param1 -> {
            dateAxis.zoomToIndexes(0, 3);
        });
    }

    protected void generateActiveUsers() {
        RadarChart chart = (RadarChart) Am4Core.create(activeUsersRow, Am4Charts.RadarChart);

        chart.dataSource.url = "data/solid-gauge.json";

        // Make chart not full circle
        chart.startAngle = -90;
        chart.endAngle = 180;
        chart.innerRadius = new Percent(20);

        // Set number format
        chart.numberFormatter.numberFormat = "#.#'%'";

        // Create axes
        CategoryAxis categoryAxis = (CategoryAxis) chart.yAxes.push(new CategoryAxis());
        categoryAxis.dataFields.category = "category";
        categoryAxis.renderer.grid.template.location = 0;
        categoryAxis.renderer.grid.template.strokeOpacity = 0;
        categoryAxis.renderer.labels.template.hide();

        Adapter<AxisLabel, AxisLabel> adapter = categoryAxis.renderer.labels.template.adapter;
        adapter.add("fill", (fill, target) -> (target.dataItem.index >= 0) ? chart.colors.getIndex(target.dataItem.index) : fill);

        categoryAxis.renderer.minGridDistance = 10;

        ValueAxis valueAxis = (ValueAxis) chart.xAxes.push(new ValueAxis());
        valueAxis.renderer.grid.template.strokeOpacity = 0;
        valueAxis.renderer.labels.template.hide();
        valueAxis.min = 0;
        valueAxis.max = 100;
        valueAxis.strictMinMax = true;

        // Create series
        RadarColumnSeries series1 = (RadarColumnSeries) chart.series.push(new RadarColumnSeries());
        series1.dataFields.valueX = "full";
        series1.dataFields.categoryY = "category";
        series1.clustered = false;
        series1.columns.template.fill = new InterfaceColorSet().getFor("alternativeBackground");
        series1.columns.template.fillOpacity = 0.08;

        series1.columns.template.strokeWidth = 0;
        series1.columns.template.radarColumn.cornerRadius = 20;

        RadarColumnSeries series2 = (RadarColumnSeries) chart.series.push(new RadarColumnSeries());
        series2.dataFields.valueX = "value";
        series2.dataFields.categoryY = "category";
        series2.clustered = false;
        series2.columns.template.strokeWidth = 0;
        series2.columns.template.tooltipText = "{category}: [bold]{value}[/]";
        series2.columns.template.radarColumn.cornerRadius = 20;

        Adapter<Column, Column> adapter1 = series2.columns.template.adapter;
        adapter1.add("fill", (fill, target) -> chart.colors.getIndex(target.dataItem.index));

        // Add cursor
        chart.cursor = new RadarCursor();
    }

    @Override
    protected void onAttach() {
        super.onAttach();
    }
}
