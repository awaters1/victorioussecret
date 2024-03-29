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
 * Doctrine_Transaction_Mysql_TestCase
 *
 * @package     Doctrine
 * @author      Konsta Vesterinen <kvesteri@cc.hut.fi>
 * @license     http://www.opensource.org/licenses/lgpl-license.php LGPL
 * @category    Object Relational Mapping
 * @link        www.phpdoctrine.org
 * @since       1.0
 * @version     $Revision$
 */
class Doctrine_Transaction_Oracle_TestCase extends Doctrine_UnitTestCase
{
    public function testCreateSavePointExecutesSql() 
    {
        $this->transaction->beginTransaction('mypoint');
        
        $this->assertEqual($this->adapter->pop(), 'SAVEPOINT mypoint');
    }
    public function testReleaseSavePointAlwaysReturnsTrue() 
    {
        $this->assertEqual($this->transaction->commit('mypoint'), true);
    }
    public function testRollbackSavePointExecutesSql() 
    {
        $this->transaction->beginTransaction('mypoint');
        $this->transaction->rollback('mypoint');

        $this->assertEqual($this->adapter->pop(), 'ROLLBACK TO SAVEPOINT mypoint');
    }
    public function testSetIsolationThrowsExceptionOnUnknownIsolationMode() 
    {
        try {
            $this->transaction->setIsolation('unknown');
            $this->fail();
        } catch(Doctrine_Transaction_Exception $e) {
            $this->pass();
        }
    }
    public function testSetIsolationExecutesSql() 
    {
        $this->transaction->setIsolation('READ UNCOMMITTED');
        $this->transaction->setIsolation('READ COMMITTED');
        $this->transaction->setIsolation('REPEATABLE READ');
        $this->transaction->setIsolation('SERIALIZABLE');

        $this->assertEqual($this->adapter->pop(), 'ALTER SESSION ISOLATION LEVEL SERIALIZABLE');
        $this->assertEqual($this->adapter->pop(), 'ALTER SESSION ISOLATION LEVEL SERIALIZABLE');
        $this->assertEqual($this->adapter->pop(), 'ALTER SESSION ISOLATION LEVEL SERIALIZABLE');
        $this->assertEqual($this->adapter->pop(), 'ALTER SESSION ISOLATION LEVEL READ COMMITTED');
    }
}
