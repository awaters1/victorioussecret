<?php

/**
 * This class has been auto-generated by the Doctrine ORM Framework
 */
abstract class BaseAlbum extends Doctrine_Record
{
  public function setTableDefinition()
  {
    $this->setTableName('albums');
    $this->hasColumn('album_id', 'integer', 8, array('type' => 'integer', 'length' => 8, 'primary' => true, 'sequence' => 'album'));
    $this->hasColumn('artist_id', 'integer', 8, array('type' => 'integer', 'length' => 8, 'notnull' => true));
    $this->hasColumn('name', 'string', 512, array('type' => 'string', 'length' => '512', 'notnull' => true));
    $this->hasColumn('date_added', 'date', null, array('type' => 'date', 'notnull' => true, 'default' => 'now()'));
    $this->hasColumn('added_by_user_id', 'integer', 8, array('type' => 'integer', 'length' => 8, 'notnull' => true));
  }
  
  public function setUp()
  {
  	$this->hasOne('Artist as Artist', array('local' => 'artist_id',
                                                    'foreign' => 'artist_id'));
    $this->hasMany('User', array('local' => 'album_id',       // <- these are the column names
                                     'foreign' => 'user_id',      // <- in the association table
                                     'refClass' => 'UserAlbum')); // <- the following line is needed in many-to-many relations!
  }

}