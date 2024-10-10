package net.pitan76.eleind.api.state;

public interface IBlockEntityActiveHolder extends IActiveHolder {
    void setActive(boolean active);

    boolean isActive();
}
