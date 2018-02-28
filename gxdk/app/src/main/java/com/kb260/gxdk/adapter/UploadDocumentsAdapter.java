package com.kb260.gxdk.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kb260.gxdk.R;
import com.kb260.gxdk.entity.UploadPic;
import com.kb260.gxdk.utils.Action;
import com.kb260.gxdk.utils.ImageLoader;
import java.util.List;

/**
 * @author  KB260
 * Created on  2017/11/14
 */

public class UploadDocumentsAdapter extends BaseQuickAdapter<UploadPic,BaseViewHolder>{
    public UploadDocumentsAdapter(@Nullable List<UploadPic> data) {
        super(R.layout.item_upload_documents, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UploadPic item) {
        switch (item.getType()){
            case Action.UPLOAD_TYPE_SFZ:
                helper.setVisible(R.id.item_uploadDocuments_tv,false);
                helper.setVisible(R.id.item_uploadDocuments_tvSfz,true);
                helper.setVisible(R.id.item_uploadDocuments_tvSfzZF,true);
                String a = "";
                switch (helper.getAdapterPosition()){
                    case 0:
                        a = "正面";
                        break;
                    case 1:
                        a = "反面";
                        break;
                    default:
                        break;
                }
                helper.setText(R.id.item_uploadDocuments_tvSfzZF,a);
                break;
            default:
                helper.setVisible(R.id.item_uploadDocuments_tv,true);
                helper.setVisible(R.id.item_uploadDocuments_tvSfz,false);
                helper.setVisible(R.id.item_uploadDocuments_tvSfzZF,false);
                break;
        }
        if (item.getUrl()!=null){
            ImageView iv = helper.getView(R.id.item_uploadDocuments_iv);
            ImageLoader.showImage(iv,item.getUrl());
        }
    }
}
