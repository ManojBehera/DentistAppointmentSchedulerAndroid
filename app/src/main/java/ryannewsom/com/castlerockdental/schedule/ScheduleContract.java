package ryannewsom.com.castlerockdental.schedule;

import java.util.List;

import model.appointment.Appointment;
import ryannewsom.com.castlerockdental.base.BasePresenter;
import ryannewsom.com.castlerockdental.base.BaseView;

/**
 * Created by Ryan on 11/21/2016.
 */

public interface ScheduleContract {

    interface View extends BaseView<Presenter> {
        List<Appointment> showScheduledAppointments(List<Appointment> scheduledAppointments);
    }

    interface Presenter extends BasePresenter {
        void scheduledAppointmentClicked(Appointment appointment);
    }
}