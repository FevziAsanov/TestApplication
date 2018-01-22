package kerchek.app.mesele.sual.testapplication.ui.base;

public interface MVPView<T extends MVPPresenter> {
    void setPresenter(T presenter);

    void indicateProgress(int messageResourceId);

    void stopProgressIndication();

    void showError(int messageTitleResource, int messageBodyResource);

    void showError(Throwable throwable);
}
