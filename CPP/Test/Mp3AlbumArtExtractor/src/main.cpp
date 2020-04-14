#include <taglib\mpeg\id3v2\id3v2tag.h>
#include <taglib\mpeg\mpegfile.h>
#include <taglib\mpeg\id3v2\id3v2frame.h>
#include <taglib\mpeg\id3v2\id3v2header.h>
#include <taglib\mpeg\id3v2\frames\attachedpictureframe.h>
#include<cstdio>
#include<string.h>

int main()
{

  static const char *IdPicture = “APIC” ;
  TagLib::MPEG::File mpegFile(“/ExampleSong.mp3”);
  TagLib::ID3v2::Tag *id3v2tag = mpegFile.ID3v2Tag();
  TagLib::ID3v2::FrameList Frame ;
  TagLib::ID3v2::AttachedPictureFrame *PicFrame ;
  void *RetImage = NULL, *SrcImage ;
  unsigned long Size ;

  FILE *jpegFile;
  jpegFile = fopen(“FromId3.jpg”,”wb”);

  if ( id3v2tag )
  {
    // picture frame
    Frame = id3v2tag->frameListMap()[IdPicture] ;
    if (!Frame.isEmpty() )
    {
      for(TagLib::ID3v2::FrameList::ConstIterator it = Frame.begin(); it != Frame.end(); ++it)
      {
        PicFrame = (TagLib::ID3v2::AttachedPictureFrame *)(*it) ;
        //  if ( PicFrame->type() ==
        //TagLib::ID3v2::AttachedPictureFrame::FrontCover)
        {
          // extract image (in it’s compressed form)
          Size = PicFrame->picture().size() ;
          SrcImage = malloc ( Size ) ;
          if ( SrcImage )
          {
            memcpy ( SrcImage, PicFrame->picture().data(), Size ) ;
            fwrite(SrcImage,Size,1, jpegFile);
            fclose(jpegFile);
            free( SrcImage ) ;
          }

        }
      }
    }
  }
  else
  {
     cout<<“id3v2 not present”;
  }
  return(0);
}

