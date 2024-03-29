<?php
/*
 *  $Id$
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * This software consists of voluntary contributions made by many individuals
 * and is licensed under the LGPL. For more information, see
 * <http://www.phpdoctrine.org>.
 */

/**
 * Doctrine_Ticket_1077_TestCase
 *
 * @package     Doctrine
 * @author      Konsta Vesterinen <kvesteri@cc.hut.fi>
 * @license     http://www.opensource.org/licenses/lgpl-license.php LGPL
 * @category    Object Relational Mapping
 * @link        www.phpdoctrine.org
 * @since       1.0
 * @version     $Revision$
 */
class Doctrine_Ticket_1077_TestCase extends Doctrine_UnitTestCase 
{
    public function prepareTables()
    {
        $this->tables[] = 'Ticket_1077_User';
        $this->tables[] = 'Ticket_1077_Phonenumber';
        parent::prepareTables();
    }

    public function testTest()
    {
        $orig = Doctrine_Manager::getInstance()->getAttribute('auto_accessor_override');
        Doctrine_Manager::getInstance()->setAttribute('auto_accessor_override', true);
        $user = new Ticket_1077_User();
        $user->username = 'jwage';
        $user->password = 'changeme';
        $user->save();
        $this->assertEqual($user->getPassword(), '4cb9c8a8048fd02294477fcb1a41191a');
        $this->assertEqual($user->getUsername(), 'Username: jwage');
        $this->assertEqual($user->username, $user->getUsername());

        try {
            $phonenumbers = $user->Phonenumbers;
            $this->fail();
        } catch (Exception $e) {
            $this->assertEqual($e->getMessage(), 'Testing that getPhonenumbers() is invoked');
        }

        $numbers = new Doctrine_Collection('Phonenumber');
        $user->Phonenumbers = $numbers;

        $this->assertIdentical($user->phonenumbersTest, $numbers);
        
        Doctrine_Manager::getInstance()->setAttribute('auto_accessor_override', $orig);
    }
}

class Ticket_1077_User extends Doctrine_Record
{
    public $phonenumbersTest = null;

    public function setTableDefinition()
    {
        $this->hasColumn('username', 'string', 255);
        $this->hasColumn('password', 'string', 255);
    }

    public function setUp()
    {
        $this->hasMany('Ticket_1077_Phonenumber as Phonenumbers', array('local'   => 'id',
                                                                        'foreign' => 'user_id'));
    }

    public function getPhonenumbers()
    {
        throw new Exception('Testing that getPhonenumbers() is invoked');
    }

    public function setPhonenumbers($phonenumbers)
    {
        $this->phonenumbersTest = $phonenumbers;
        return $this->_set('Phonenumbers', $phonenumbers);
    }

    public function getUsername($load = true)
    {
        return 'Username: ' . $this->_get('username', $load);
    }

    public function setPassword($password)
    {
        return $this->_set('password', md5($password));
    }

    public function getPassword($load = true)
    {
        return $this->_get('password', $load);
    }
}

class Ticket_1077_Phonenumber extends Doctrine_Record
{
    public function setTableDefinition()
    {
        $this->hasColumn('phonenumber', 'string', 55);
        $this->hasColumn('user_id', 'integer');
    }

    public function setUp()
    {
        $this->hasOne('Ticket_1077_User as User', array('local'   => 'user_id',
                                                        'foreign' => 'id'));
    }
}