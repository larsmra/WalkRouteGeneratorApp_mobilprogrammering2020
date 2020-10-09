package no.hiof.larsmra.walkroutegenerator.ui.friendlist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FriendListViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FriendListViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is friend list fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}