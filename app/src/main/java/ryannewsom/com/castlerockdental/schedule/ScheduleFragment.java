package ryannewsom.com.castlerockdental.schedule;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.appointment.Appointment;
import ryannewsom.com.castlerockdental.R;
import ryannewsom.com.castlerockdental.appointments.AppointmentContract;
import ryannewsom.com.castlerockdental.appointments.AppointmentFragment;


/**
 * Displays the current Scheduled Appointments for the business
 */
public class ScheduleFragment extends Fragment implements AppointmentContract.View, AppointmentAdapterClickListener {
    private AppointmentContract.Presenter mPresenter;
    @BindView(R.id.schedule_recycler_view)
    public RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh)
    public SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public ScheduleFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_schedule, container, false);
        ButterKnife.bind(this, v);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.refreshUI();
            }
        });

        getActivity().setTitle(getString(R.string.scheduled_appointments));
        mSwipeRefreshLayout.setRefreshing(true);

        mPresenter.refreshUI();

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void initRecyclerView() {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
    }


    @Override
    public void setPresenter(AppointmentContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showAppointments(List<Appointment> scheduledAppointments) {
        // specify an adapter (see also next example)
        mSwipeRefreshLayout.setRefreshing(false);

        mAdapter = new AppointmentAdapter(this, scheduledAppointments);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAppointmentClicked(Appointment appointment) {
        getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.content_main,
                AppointmentFragment.newInstance(appointment)).commit();
    }
}
