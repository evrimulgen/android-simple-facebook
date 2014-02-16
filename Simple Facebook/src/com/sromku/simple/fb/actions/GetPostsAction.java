package com.sromku.simple.fb.actions;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import android.os.Bundle;

import com.facebook.Response;
import com.facebook.model.GraphObject;
import com.sromku.simple.fb.SessionManager;
import com.sromku.simple.fb.entities.Post;
import com.sromku.simple.fb.utils.GraphPath;
import com.sromku.simple.fb.utils.Utils;

public class GetPostsAction extends GetAction<List<Post>> {

    public GetPostsAction(SessionManager sessionManager) {
	super(sessionManager);
    }

    @Override
    protected String getGraphPath() {
	return getTarget() + "/" + GraphPath.POSTS;
    }

    @Override
    protected Bundle getBundle() {
	Bundle bundle = new Bundle();
	bundle.putString("date_format", "U");
	return bundle;
    }

    @Override
    protected List<Post> processResponse(Response response) throws JSONException {
	List<GraphObject> graphObjects = Utils.typedListFromResponse(response, GraphObject.class);
	List<Post> posts = new ArrayList<Post>(graphObjects.size());
	for (GraphObject graphObject : graphObjects) {
	    Post post = Post.create(graphObject);
	    posts.add(post);
	}
	return posts;
    }

}
