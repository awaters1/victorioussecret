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
 * Doctrine_Ticket_1523_TestCase
 *
 * @package     Doctrine
 * @author      Konsta Vesterinen <kvesteri@cc.hut.fi>
 * @license     http://www.opensource.org/licenses/lgpl-license.php LGPL
 * @category    Object Relational Mapping
 * @link        www.phpdoctrine.org
 * @since       1.0
 * @version     $Revision$
 */
class Doctrine_Ticket_1523_TestCase extends Doctrine_UnitTestCase 
{
    public function testTest()
    {
        $q = Doctrine_Query::create()
            ->from('Ticket_1523_User u')
            ->where('EXISTS (SELECT ug.user_id FROM Ticket_1523_UserGroup ug LEFT JOIN ug.Group g WHERE ug.user_id = u.id AND g.name = \'Test\')')
            ->orderBy('u.username ASC');
        $this->assertEqual($q->getSql(), 'SELECT t.id AS t__id, t.username AS t__username FROM ticket_1523__user t WHERE EXISTS (SELECT t2.user_id AS t2__user_id FROM ticket_1523__user_group t2 LEFT JOIN ticket_1523__group t3 ON t2.group_id = t3.id WHERE (t2.user_id = t.id AND t3.name = \'Test\')) ORDER BY t.username ASC');
    }
}

class Ticket_1523_User extends Doctrine_Record
{
    public function setTableDefinition()
    {
        $this->hasColumn('username', 'string', 255);
    }

    public function setUp()
    {
        $this->hasMany('Ticket_1523_Group as Groups', array('local'    => 'user_id',
                                                            'foreign'  => 'group_id',
                                                            'refClass' => 'Ticket_1523_UserGroup'));
    }
}

class Ticket_1523_Group extends Doctrine_Record
{
    public function setTableDefinition()
    {
        $this->hasColumn('name', 'string', 255);
    }

    public function setUp()
    {
        $this->hasMany('Ticket_1523_User as Users', array('local'    => 'group_id',
                                                          'foreign'  => 'user_id',
                                                          'refClass' => 'Ticket_1523_UserGroup'));
    }
}


class Ticket_1523_UserGroup extends Doctrine_Record
{
    public function setTableDefinition()
    {
        $this->hasColumn('user_id', 'integer');
        $this->hasColumn('group_id', 'integer');
    }

    public function setUp()
    {
        $this->hasOne('Ticket_1523_User as User', array('local'   => 'user_id',
                                                        'foreign' => 'id'));

        $this->hasOne('Ticket_1523_Group as Group', array('local'   => 'group_id',
                                                          'foreign' => 'id'));
    }
}