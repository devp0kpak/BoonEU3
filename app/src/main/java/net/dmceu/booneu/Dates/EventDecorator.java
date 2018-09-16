package net.dmceu.booneu.Dates;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.res.ResourcesCompat;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import net.dmceu.booneu.R;

import java.util.Collection;
import java.util.HashSet;

public class EventDecorator implements DayViewDecorator {

    private int color;
    private final HashSet<CalendarDay> dates;
    private Context context;

    public EventDecorator(int color, Context context,Collection<CalendarDay> dates) {
        this.color = color;
        this.dates = new HashSet<>(dates);
        this.context = context;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        //view.addSpan(new DotSpan(5, ContextCompat.getColor(context, color)));

        GradientDrawable drawable = (GradientDrawable) ResourcesCompat.getDrawable(context.getResources(), R.drawable.bg_span, null);
        //drawable.setColor(color);
        view.setBackgroundDrawable(drawable);
    }
}
