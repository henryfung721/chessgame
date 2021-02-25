package com.example.chessgame.constructor;

public class move_record {
        private String msteps;
        private int mImageResource;
        private String mtext1;
        private String mtext2;
        private int meatImageResource;
        public move_record(String steps,int ImageResource,String text1,String text2,int eatImageResource){
            msteps=steps;
            mImageResource=ImageResource;
            mtext1=text1;
            mtext2=text2;
            meatImageResource=eatImageResource;
        }
        public String getstep(){
            return msteps;
        }

        public int getImageResource(){
            return mImageResource;
        }

        public String getText1(){
            return mtext1;
        }

        public String getText2(){
            return mtext2;
        }

        public int geteatImageResource(){return  meatImageResource; }

}
