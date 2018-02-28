package com.kb260.kbutils.widget.swipe.interfaces;

import com.kb260.kbutils.widget.swipe.SwipeLayout;
import com.kb260.kbutils.widget.swipe.implments.SwipeItemMangerImpl;
import com.kb260.kbutils.widget.swipe.util.Attributes;

import java.util.List;

public interface SwipeItemMangerInterface {

    public void openItem(int position);

    public void closeItem(int position);

    public void closeAllExcept(SwipeLayout layout);
    
    public void closeAllItems();

    public List<Integer> getOpenItems();

    public List<SwipeLayout> getOpenLayouts();

    public void removeShownLayouts(SwipeLayout layout);

    public boolean isOpen(int position);

    public Attributes.Mode getMode();

    public void setMode(Attributes.Mode mode);
}
