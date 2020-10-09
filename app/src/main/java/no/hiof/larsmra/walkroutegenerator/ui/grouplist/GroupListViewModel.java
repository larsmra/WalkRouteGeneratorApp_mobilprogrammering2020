package no.hiof.larsmra.walkroutegenerator.ui.grouplist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GroupListViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public GroupListViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is group list fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}