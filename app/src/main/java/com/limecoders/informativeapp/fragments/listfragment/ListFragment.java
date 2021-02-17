package com.limecoders.informativeapp.fragments.listfragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.limecoders.informativeapp.MainRecipeAdapter;
import com.limecoders.informativeapp.R;
import com.limecoders.informativeapp.RecipeAdapter;
import com.limecoders.informativeapp.Utils;
import com.limecoders.informativeapp.pojo.MainRecipeModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ListFragment extends Fragment {

    private ListViewModel mViewModel;
    private RecyclerView recipeRecyclerView;


    private List<MainRecipeModel> models = new ArrayList<>();
    private MainRecipeAdapter adapter;

    public static ListFragment newInstance() {
        return new ListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);

        Utils.initpDialog(getActivity(),"Loading...");
        Utils.showpDialog();

        recipeRecyclerView = view.findViewById(R.id.recipeRecyclerView);

        recipeRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recipeRecyclerView.setHasFixedSize(true);
        adapter = new MainRecipeAdapter(getActivity(),true);
        recipeRecyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ListViewModel.class);
        // TODO: Use the ViewModel

        mViewModel.getRecipeModel().observe(getViewLifecycleOwner(), new Observer<List<MainRecipeModel>>() {
            @Override
            public void onChanged(List<MainRecipeModel> modelList) {
                adapter.setModelList(modelList);
                Utils.hidepDialog();
            }
        });

    }

//    private void set1stRecipes() {
//
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("MainRecipes");
//
//        String id = UUID.randomUUID().toString();
//
//        HashMap<String,Object> map = new HashMap<>();
//        map.put("id",id);
//        map.put("name","");
//        map.put("imageUrl","https://firebasestorage.googleapis.com/v0/b/informative-app-c87f7.appspot.com/o/recipe.jpg?alt=media&token=788d50a8-3cff-41d2-9d2f-07f3da1f17f4");
//        map.put("subCategoriesId","6c722cb6-855d-4a72-bc27-05d5a9dd6002,a6b12671-e650-4c44-9913-edadfe653c19,cf50923d-d005-496c-bfef-dca7ae8fd4d1,00dec650-5e7f-4ae1-81d9-b257f59d9336,f70fe959-82fa-48d7-9b00-ee7d709203fd,9185594e-998f-408d-9851-2a65c1860263,aef4b403-b92c-490e-9915-23253493942c");
//
//        reference.child(id).setValue(map);
//    }
//    private void set2ndRecipes() {
//
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("MainRecipes");
//
//        String id = UUID.randomUUID().toString();
//
//        HashMap<String,Object> map = new HashMap<>();
//        map.put("id",id);
//        map.put("name","");
//        map.put("imageUrl","https://firebasestorage.googleapis.com/v0/b/informative-app-c87f7.appspot.com/o/37MaLaiFengGuang.jpg?alt=media&token=016b52f8-021c-4ccb-9529-be26186fc236");
//        map.put("subCategoriesId","7a1b0357-1606-4eba-9058-084725871283,3c12131e-b64d-4817-8acd-ad7589d6cf6e,02aa48ce-ab40-41ea-84f7-422e61ca4433,1e189e06-320a-42b6-a613-a3cd1911945c,3ece9f13-ea8a-4b26-89ab-fc47eccbe714,14ca0bac-2e27-4210-8f47-5a2590e551d0,1710de6e-05e7-4fe4-807c-df0a5c37ba50,4557127c-c877-4675-8c61-8bee8040db92,48df5c81-398a-4da6-874b-a0975602001d,420a3c8f-20ce-43fe-bb3d-da299c2e0ef8,5282dbb8-8bc5-4ff4-91f4-7cecd577a6cf,88965d24-bb7c-48aa-bf4e-2d3bbe82fd4e,4047e18f-a554-44af-99cf-1cc42d98d234,057248f1-f7ae-4ffb-9a6c-30dbcaa84686,23d74c02-e850-4514-abb0-af9bf2f13ef3,a4ce394c-0e48-4048-9d8e-32a36e12ed9e,f5b2da77-ffe4-4739-971e-4c137eddf148,bb10ca1a-4a08-4eca-8248-b0b38ddcaaf2,6ed2cfcb-af62-4dc9-849f-264afab3907f");
//
//        reference.child(id).setValue(map);
//    }
//    private void set3rdRecipes() {
//
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("MainRecipes");
//
//        String id = UUID.randomUUID().toString();
//
//        HashMap<String,Object> map = new HashMap<>();
//        map.put("id",id);
//        map.put("name","");
//        map.put("imageUrl","https://firebasestorage.googleapis.com/v0/b/informative-app-c87f7.appspot.com/o/11RiBenTouFuJingZhenGu.jpg?alt=media&token=69b16c77-e446-4ff5-9164-94c3f02218ce");
//        map.put("subCategoriesId","780020ac-ecb8-4da9-b6df-d66da2ef1343,13db587c-b33b-4615-90ec-de57e002209a,3d6dd76d-f572-4b19-8799-a3b76e306849,24ba89bf-f32b-492a-88bf-e93a6e9d6475,5282dbb8-8bc5-4ff4-91f4-7cecd577a6cf,40a960f1-625c-48ef-b79d-e25b1a1602e3,a5961a65-6d95-40bf-b785-5a44a3b6a07f,99afb4ed-e0a0-4c74-9305-dc66d46a11dc,b491412d-a397-4ac1-9e61-c891f8e23d76,aef4b403-b92c-490e-9915-23253493942c");
//
//        reference.child(id).setValue(map);
//    }
//    private void set4thRecipes() {
//
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("MainRecipes");
//
//        String id = UUID.randomUUID().toString();
//
//        HashMap<String,Object> map = new HashMap<>();
//        map.put("id",id);
//        map.put("name","");
//        map.put("imageUrl","https://firebasestorage.googleapis.com/v0/b/informative-app-c87f7.appspot.com/o/27KeLeJiChi.jpg?alt=media&token=d2c0101b-1cc1-4d94-879e-93b4cd8bbfbd");
//        map.put("subCategoriesId","f76a4073-0268-4e8d-b206-0c7b0069d56d,21888377-b94b-470b-8da3-d9bb74514ad8,7099cbf8-92fd-49c8-84a2-14bcd634a360,59df16b3-a3d2-4e3d-9502-f6da0815d3f5,6c526282-cc57-4126-aecf-899475998e71,0e1c0492-7bac-4153-89c7-22677b40d936,a5961a65-6d95-40bf-b785-5a44a3b6a07f,3ece9f13-ea8a-4b26-89ab-fc47eccbe714,249b4dcd-5fe6-40e7-b957-137b113dca19,663792c3-5a0c-48b6-a038-9a627a9a6ae5,f7386fe9-cfcc-4d2f-9743-425f5f146083,807ff65b-e4b9-4661-ba31-1358123ff154,2feeba92-c20b-4d2d-9b45-7ae7d9522928,924b1139-4ecf-476c-bae1-04f08276f890,ac529c8d-7872-42df-9d8d-dd6eb378c3e0");
//
//        reference.child(id).setValue(map);
//    }
//    private void set5thRecipes() {
//
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("MainRecipes");
//
//        String id = UUID.randomUUID().toString();
//
//        HashMap<String,Object> map = new HashMap<>();
//        map.put("id",id);
//        map.put("name","");
//        map.put("imageUrl","https://firebasestorage.googleapis.com/v0/b/informative-app-c87f7.appspot.com/o/13YuMiPaiGuTang.jpg?alt=media&token=405b2693-d3f5-453d-a5cc-3ebc16a80275");
//        map.put("subCategoriesId","70f015e0-6fb8-46de-839e-2575437cb4f0,823f7104-20a5-4ff5-beb8-a9ae2292c70e,24ba89bf-f32b-492a-88bf-e93a6e9d6475,ea658513-84b0-4cd4-8e3b-f9ad0ca1ec7e,27e13ada-44bf-4f2d-bfad-42f31b661ca6,78c9e89a-9b93-4948-bba2-d1c3567b48e7,0111f20d-11c7-4c90-8edb-d19ceb59fc54,cbba2d76-b1f8-470c-8c43-b51c31a0ebdb");
//
//        reference.child(id).setValue(map);
//    }
//    private void set6thRecipes() {
//
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("MainRecipes");
//
//        String id = UUID.randomUUID().toString();
//
//        HashMap<String,Object> map = new HashMap<>();
//        map.put("id",id);
//        map.put("name","");
//        map.put("imageUrl","https://firebasestorage.googleapis.com/v0/b/informative-app-c87f7.appspot.com/o/39SuanLaLALA.jpg?alt=media&token=687f8263-1485-43e9-90e2-f2edfbd96722");
//        map.put("subCategoriesId","9c9ac149-ad4b-4504-bb6a-f61f072ef1ef,9260f061-f596-4183-bca0-0357ad13801f,3d6dd76d-f572-4b19-8799-a3b76e306849,3839ef4a-5be4-4eef-a402-8ee3f6115c6e,cd35056c-4b4b-4a95-877a-645030893544,aaef691e-49aa-43b8-9d2e-df422c2b6167,38f13b5a-31fe-485b-b418-c774da7e4e8d,e46432ca-3e11-4a02-af2d-09f0859612f4,240506bf-908a-4471-9c6d-8819adb10a3c,6ed2cfcb-af62-4dc9-849f-264afab3907f");
//
//        reference.child(id).setValue(map);
//    }
//    private void set7thRecipes() {
//
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("MainRecipes");
//
//        String id = UUID.randomUUID().toString();
//
//        HashMap<String,Object> map = new HashMap<>();
//        map.put("id",id);
//        map.put("name","");
//        map.put("imageUrl","https://firebasestorage.googleapis.com/v0/b/informative-app-c87f7.appspot.com/o/15NanRuZhaRou.jpg?alt=media&token=34227fd2-7ddf-44c2-9b88-fc860eb2d5cb");
//        map.put("subCategoriesId","200fdb59-922a-40ae-a37c-1e0c86ddce3f,780020ac-ecb8-4da9-b6df-d66da2ef1343,d1a85e8b-124c-475a-8ac2-48ed79fff941,1220c55e-f5ea-4344-bc55-ba10665e3d74,7ab0ee86-83eb-4d0a-b1e0-2fa31b72ffdf,fb91d4d3-713f-4432-82ef-29e1274e14ca,40a960f1-625c-48ef-b79d-e25b1a1602e3,7f1d78b5-144a-4d2b-86ad-4b1a6652dbdd,879cc7ae-6ea5-4bff-9e79-971a9396948f,0a7df9bd-c3ed-491b-b13a-de6f4398141a,99afb4ed-e0a0-4c74-9305-dc66d46a11dc,d17a65bc-877e-4632-8c86-3ac6aa6c5786");
//
//        reference.child(id).setValue(map);
//    }
//    private void set8thRecipes() {
//
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("MainRecipes");
//
//        String id = UUID.randomUUID().toString();
//
//        HashMap<String,Object> map = new HashMap<>();
//        map.put("id",id);
//        map.put("name","");
//        map.put("imageUrl","https://firebasestorage.googleapis.com/v0/b/informative-app-c87f7.appspot.com/o/61HeiNuoMi.jpg?alt=media&token=a862ecc6-7e7d-445e-a0d4-3d6d2d4e3816");
//        map.put("subCategoriesId","f3bf971b-6e2a-4d9b-a904-2154633319c2,f2efa30d-49a5-440b-bc8c-8bd37f2ab0b5,162d9c84-c13c-4a9b-96d3-7e654f3bbfce,aaef691e-49aa-43b8-9d2e-df422c2b6167,96585f6b-4e99-44c2-870b-98fe5519b292,85f834a2-81ab-4c39-ac70-6244d946d37a,18b70d08-ed8e-4316-9ec2-6e9bb1b8ffa6");
//
//        reference.child(id).setValue(map);
//    }

}